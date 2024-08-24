package com.cca.paymentcore.service.refreshtokenservice;

import com.cca.paymentcore.builder.ResponseBuilderFactory;
import com.cca.paymentcore.constants.Constants;
import com.cca.paymentcore.constants.ResponseConstant;
import com.cca.paymentcore.dto.responsedto.ResponseDTO;
import com.cca.paymentcore.exception.ResponseException;
import com.cca.paymentcore.service.jwttokenservice.JwtTokenService;
import com.cca.paymentcore.service.loggerservice.LogService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {

    private final String className = this.getClass().getSimpleName();
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogService logService;
    @Autowired
    private JwtTokenService jwtTokenService;


    public ResponseDTO refreshToken() {
        try {
            logService.printInfoLogs(request, className, "refreshToken API started processing");
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
            return jwtTokenService.refreshToken(token);
        } catch (ResponseException responseException) {
            logService.printResponseErrorLogs(request, className, responseException.getMessage());
            return ResponseBuilderFactory.getResponse(responseException.getMessage(), responseException.getResponseCode());
        } catch (Exception e) {
            logService.printErrorLogs(request, className, e);
            return ResponseBuilderFactory.getResponse(Constants.FAILURE_STATUS, ResponseConstant.FAILURE);
        }
    }
}
