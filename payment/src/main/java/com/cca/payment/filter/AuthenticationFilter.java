package com.cca.payment.filter;

import com.cca.payment.builder.ResponseBuilderFactory;
import com.cca.payment.constants.ApplicationConstants;
import com.cca.payment.constants.Constants;
import com.cca.payment.constants.ResponseConstant;
import com.cca.payment.dto.responsedto.ResponseDTO;
import com.cca.payment.exception.ResponseException;

import com.cca.payment.repo.userauth.UserAuthRepository;
import com.cca.payment.service.loggerservice.LogService;
import com.cca.payment.service.verifytokenservice.VerifyTokenService;
import com.cca.payment.utility.Utility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.multipart.MultipartResolver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;
import java.util.logging.Level;

@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE - 1)
@SuppressWarnings("java:S6813")
public class AuthenticationFilter extends GenericFilterBean {

    private final String className = this.getClass().getSimpleName();
    @Autowired
    ResponseBuilderFactory responseBuilderFactory;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    LogService logService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private VerifyTokenService verifyTokenService;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    private MultipartResolver multipartResolver;

    @Override
    public void doFilter(
            ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            request.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
            request.setAttribute(ApplicationConstants.REQUEST_START_TIME, System.currentTimeMillis());
            final String authorization = request.getHeader(Constants.AUTHORIZATION);

            Cookie[] cookies = request.getCookies();
            addCookiesToResponse(response, cookies);
            String sessionId = request.getHeader("sessionId");
            String operationId = request.getHeader("operationId");
            MDC.put("operationId", operationId);
            logService.printInfoLogs(
                    request, className, "Session Id ::" + sessionId + " Request Id ::" + operationId);
            final String requestedURL = request.getRequestURI();
            String serverName = request.getServerName();

            Thread.currentThread().setName(requestedURL);

            if (checkIfRequestedUrlContainsSwaggerOrHealthCheck(
                    requestedURL, serverName, authorization)) {
                chain.doFilter(request, response);
                return;
            } else if (validateUserToken()) {
                chain.doFilter(request, response);
                return;
            }

        } catch (ResponseException responseException) {
            logService.printResponseErrorLogs(
                    request, className, responseException.getMessage());
            getRequestFailureObject(
                    response, responseException.getMessage(), null, Integer.parseInt(responseException.getResponseCode()));
        } catch (Exception e) {
            logService.printErrorLogs(
                    request, className, e);
            getRequestFailureObject(
                    response, Constants.FAILURE_STATUS, null, Integer.parseInt(ResponseConstant.FAILURE));
        } finally {
            // Clear MDC to avoid leaking between requests.
            MDC.clear();
        }
    }

    private boolean validateUserToken() {
        ResponseDTO responseDTO = verifyTokenService.verifyToken();

        if (responseDTO.getResponseCode() == Integer.parseInt(ResponseConstant.SUCCESS)) {
            return true;
        } else {
            throw new ResponseException(responseDTO.getMessage(), String.valueOf(responseDTO.getResponseCode()));
        }


    }

    /**
     * @param response
     * @param cookies
     */
    private void addCookiesToResponse(HttpServletResponse response, Cookie[] cookies) {
        if (cookies != null)
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setMaxAge(0);
                cookies[i].setSecure(true);
                cookies[i].setValue(null);

                response.addCookie(cookies[i]);
            }
    }

    /**
     * @param requestedURL
     * @param authorization
     * @return
     */
    private boolean checkIfRequestedUrlContainsSwaggerOrHealthCheck(
            final String requestedURL, final String serverName, String authorization)
            throws JsonProcessingException {
        if (requestedURL.contains("/transaction")) {
            return false;
        }
        return true;
    }


    /**
     * Method to GetRequestFailure Object
     *
     * @param response
     * @param message
     * @param data
     * @param messCode
     * @return
     */
    private void getRequestFailureObject(
            ServletResponse response, String message, Object data, int messCode) {
        try {
            TreeMap<String, Object> resultMap = new TreeMap<>();
            resultMap.put(Constants.RESPONSE_STATUS, Constants.FAILURE_STATUS);
            resultMap.put(Constants.RESPONSE_MSG, message);
            resultMap.put(Constants.RESPONSE_DATA, data);
            resultMap.put(Constants.RESPONSE_CODE, messCode);

            byte[] responseToSend = restResponseBytes(resultMap);
            ((HttpServletResponse) response)
                    .setHeader(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON);
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);
            response.getOutputStream().write(responseToSend);
        } catch (IOException ioex) {
            try {
                response.getOutputStream().write(restResponseBytes("Something went wrong !!!"));
            } catch (IOException e) {
                log.error(
                        Constants.LOG_NEW_PREFIX,
                        Utility.getCurrentTimeStampObject(),
                        Level.INFO,
                        Utility.getIp(),
                        Utility.getRequestId(request),
                        null,
                        null,
                        className,
                        "Inside Filter"
                                + Constants.ERROR_WHILE_RETREIVING_REQUEST_FAILURE_OBJECT
                                + ExceptionUtils.getStackTrace(e));
            }
        }
    }

    private byte[] restResponseBytes(Object eErrorResponse) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
        return serialized.getBytes();
    }
}
