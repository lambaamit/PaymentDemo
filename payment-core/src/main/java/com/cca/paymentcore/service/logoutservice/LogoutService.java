package com.cca.paymentcore.service.logoutservice;

import com.cca.paymentcore.builder.ResponseBuilderFactory;
import com.cca.paymentcore.constants.ApplicationConstants;
import com.cca.paymentcore.constants.Constants;
import com.cca.paymentcore.constants.ResponseConstant;
import com.cca.paymentcore.dto.responsedto.ResponseDTO;
import com.cca.paymentcore.encryption.OclDfsPIIEncryption;
import com.cca.paymentcore.entity.oauth.AccessToken;
import com.cca.paymentcore.exception.ResponseException;
import com.cca.paymentcore.repo.oauth.OauthAccessTokenRepository;
import com.cca.paymentcore.repo.userauth.UserAuthRepository;
import com.cca.paymentcore.service.jwttokenservice.JwtTokenService;
import com.cca.paymentcore.service.loggerservice.LogService;
import com.cca.paymentcore.service.validationservice.HeaderValidationService;
import com.cca.paymentcore.service.verifytokenservice.VerifyTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {

    private final String className = this.getClass().getSimpleName();
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogService logService;
    @Autowired
    private VerifyTokenService verifyTokenService;
    @Autowired
    private HeaderValidationService headerValidationService;
    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private OauthAccessTokenRepository oauthAccessTokenRepository;
    @Autowired
    private JwtTokenService jwtTokenService;

    public ResponseDTO logout() {
        try {
            var username = StringUtils.EMPTY;
            headerValidationService.validateHeader();
            var uid = request.getHeader("UID");
            var channel = request.getHeader("channel");
            var authorization = request.getHeader("Authorization");
            if (StringUtils.isBlank(authorization) || !authorization.startsWith("Bearer ")) {
                logService.printResponseErrorLogs(request, className, "Authorization Header is missing");
                return ResponseBuilderFactory.getResponse(Constants.INVALID_SESSION, ResponseConstant.INVALID_TOKEN_CODE);
            }
            var token = authorization.substring(7);
            if (validateToken(token))
                return ResponseBuilderFactory.getResponse(Constants.INVALID_SESSION, ResponseConstant.INVALID_TOKEN_CODE);
            username = getUsername(token);
            if (validateAndUpdateToken(username, token))
                return ResponseBuilderFactory.getResponse(ApplicationConstants.TOKEN_INVALID, ResponseConstant.TOKEN_INVALID);
            return ResponseBuilderFactory.getResponse(ApplicationConstants.SUCCESS, ResponseConstant.SUCCESS);
        } catch (ResponseException responseException) {
            logService.printResponseErrorLogs(request, className, responseException.getMessage());
            return ResponseBuilderFactory.getResponse(ApplicationConstants.FAIL, ResponseConstant.FAILURE);
        } catch (Exception e) {
            logService.printErrorLogs(request, className, e);
            return ResponseBuilderFactory.getResponse(ApplicationConstants.FAIL, ResponseConstant.FAILURE);
        }
        // Method to logout
    }

    private String getUsername(String token) {
        String username;
        username = jwtTokenService.getNameFromToken(token);
        username = OclDfsPIIEncryption.decrypt(username);
        return username;
    }

    private boolean validateAndUpdateToken(String username, String token) {
        AccessToken oauthAccessToken = oauthAccessTokenRepository.findByUsername(username);
        if (ObjectUtils.isNotEmpty(oauthAccessToken)) {
            if (StringUtils.isNotBlank(oauthAccessToken.getToken()) && StringUtils.equalsIgnoreCase(oauthAccessToken.getToken(), token)) {
                oauthAccessTokenRepository.delete(oauthAccessToken);
                return false;
            }
        }
        return true;
    }

    private boolean validateToken(String token) {
        if (StringUtils.isBlank(token)) {
            logService.printResponseErrorLogs(request, className, "Token is missing");
            return true;
        }
        return false;
    }

}
