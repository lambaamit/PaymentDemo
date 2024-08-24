package com.cca.payment.service.verifytokenservice;

import com.cca.payment.builder.ResponseBuilderFactory;
import com.cca.payment.constants.Constants;
import com.cca.payment.constants.ResponseConstant;
import com.cca.payment.dto.responsedto.ResponseDTO;
import com.cca.payment.entity.userauth.UserAuth;
import com.cca.payment.exception.ResponseException;
import com.cca.payment.repo.userauth.UserAuthRepository;
import com.cca.payment.service.jwttokenservice.JwtTokenService;
import com.cca.payment.service.loggerservice.LogService;
import com.cca.payment.service.validationservice.HeaderValidationService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyTokenService {
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogService logService;
    private final String className = this.getClass().getSimpleName();
    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private HeaderValidationService headerValidationService;


    public ResponseDTO verifyToken() {
        try {
            logService.printInfoLogs(request, className, "verifyToken API started processing");
            headerValidationService.validateHeader();
            var authorization = request.getHeader("Authorization");
            if (StringUtils.isBlank(authorization) || !authorization.startsWith("Bearer ")) {
                logService.printResponseErrorLogs(request, className, "Authorization Header is missing");
                return ResponseBuilderFactory.getResponse(Constants.INVALID_SESSION, ResponseConstant.INVALID_TOKEN_CODE);
            }
            var token = authorization.substring(7);
            if (StringUtils.isBlank(token)) {
                logService.printResponseErrorLogs(request, className, "Token is missing");
                return ResponseBuilderFactory.getResponse(Constants.INVALID_SESSION, ResponseConstant.INVALID_TOKEN_CODE);
            }
            var response = jwtTokenService.verifyToken(token);

            if (StringUtils.equals(response, Constants.FAILURE_STATUS)) {
                return ResponseBuilderFactory.getResponse(Constants.INVALID_SESSION, ResponseConstant.INVALID_TOKEN_CODE);
            } else {
                return getVerificationResponse(response);
            }

        } catch (ResponseException responseException) {
            logService.printResponseErrorLogs(request, className, responseException.getMessage());
            return ResponseBuilderFactory.getResponse(responseException.getMessage(), responseException.getResponseCode());
        } catch (Exception e) {
            logService.printErrorLogs(request, className, e);
            return ResponseBuilderFactory.getResponse(Constants.FAILURE_STATUS, ResponseConstant.FAILURE);
        }
    }

    private ResponseDTO getVerificationResponse(String response) {
        UserAuth userAuth = userAuthRepository.findByUsername(response);
        if (ObjectUtils.isEmpty(userAuth)) {
            return ResponseBuilderFactory.getResponse(Constants.INVALID_SESSION, ResponseConstant.INVALID_TOKEN_CODE);
        }
        String deviceId = request.getHeader("deviceId");
        String channel = request.getHeader("channel");

        if (StringUtils.isBlank(deviceId) || StringUtils.isBlank(channel)) {
            logService.printResponseErrorLogs(request, className, "DeviceId or Channel is missing");
            return ResponseBuilderFactory.getResponse(Constants.INVALID_SESSION, ResponseConstant.INVALID_TOKEN_CODE);
        }
        if (!StringUtils.equals(userAuth.getDeviceIid(), deviceId) || !StringUtils.equals(userAuth.getChannel(), channel)) {
            logService.printResponseErrorLogs(request, className, "DeviceId or Channel is Mismatched");
            return ResponseBuilderFactory.getResponse(Constants.INVALID_SESSION, ResponseConstant.INVALID_TOKEN_CODE);
        }

        return ResponseBuilderFactory.getResponse(Constants.SUCCESS_STATUS, ResponseConstant.SUCCESS);
    }
}
