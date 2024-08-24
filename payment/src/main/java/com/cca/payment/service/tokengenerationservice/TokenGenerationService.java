package com.cca.payment.service.tokengenerationservice;

import com.cca.payment.builder.ResponseBuilderFactory;
import com.cca.payment.constants.Constants;
import com.cca.payment.constants.ResponseConstant;
import com.cca.payment.dto.TokenResponse;
import com.cca.payment.dto.logindto.LoginRequestDTO;
import com.cca.payment.dto.responsedto.ResponseDTO;
import com.cca.payment.encryption.OclDfsPIIEncryption;
import com.cca.payment.entity.clientdetail.ClientDetail;
import com.cca.payment.entity.oauth.AccessToken;
import com.cca.payment.exception.ResponseException;
import com.cca.payment.repo.ClientDetailRepo.ClientDetailsRepository;
import com.cca.payment.repo.oauth.OauthAccessTokenRepository;
import com.cca.payment.service.jwttokenservice.JwtTokenService;
import com.cca.payment.service.loggerservice.LogService;
import com.cca.payment.service.validateuserservice.ValidateUserCredService;
import com.cca.payment.service.validationservice.HeaderValidationService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenGenerationService {

    @Autowired
    private LogService logService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private OauthAccessTokenRepository oauthAccessTokenRepository;
    @Autowired
    private ClientDetailsRepository clientDetailsRepository;
    @Autowired
    private HeaderValidationService headerValidationService;
    @Autowired
    private ValidateUserCredService validateUserCredService;


    public ResponseDTO generateToken(LoginRequestDTO loginRequestDTO) {
        try {
            headerValidationService.validateHeader();
            logService.printInfoLogs(null, this.getClass().getSimpleName(), "Initiating Token Generation");
            boolean isValidUser = validateUserCredService.validateUser(loginRequestDTO.getLoginId(), loginRequestDTO.getPassword());
            if (isValidUser) {
                String deviceId = request.getHeader("deviceId");
                String channel = request.getHeader("channel");

                ClientDetail clientDetail = getDetails();
                Map<String, Object> additionalClaims = new HashMap<>();
                updateClaims(loginRequestDTO, additionalClaims, deviceId, channel);
                // Generate Token
                TokenResponse tokenResponse = jwtTokenService.generateToken(loginRequestDTO.getLoginId(), additionalClaims
                        , clientDetail.getAccessTokenValidityInSeconds(), clientDetail.getRefreshTokenValidityInSeconds());
                logService.printInfoLogs(null, this.getClass().getSimpleName(), "Token Generated Successfully");
                storeAccessTokenDetails(loginRequestDTO, tokenResponse);
                return ResponseBuilderFactory.getResponse(Constants.SUCCESS_STATUS, ResponseConstant.SUCCESS_CODE, tokenResponse);
            } else {
                logService.printInfoLogs(null, this.getClass().getSimpleName(), "Invalid User");
                return ResponseBuilderFactory.getResponse(Constants.UNAUTHORIZED_LOGIN, ResponseConstant.UNAUTHORIZED_LOGIN);
            }
        } catch (ResponseException responseException) {
            logService.printErrorLogs(null, this.getClass().getSimpleName(), responseException);
            return ResponseBuilderFactory.getResponse(responseException.getMessage(), responseException.getResponseCode());
        } catch (Exception e) {
            logService.printErrorLogs(null, this.getClass().getSimpleName(), e);
            return ResponseBuilderFactory.getResponse(Constants.UNAUTHORIZED_LOGIN, ResponseConstant.UNAUTHORIZED_LOGIN);
        }

    }

    private void setUidForApp(TokenResponse tokenResponse) {
        if (StringUtils.equals(request.getHeader("channel"), "APP")) {
            String uid = request.getAttribute("uid").toString();
            tokenResponse.setUid(uid);
        }
    }

    private void updateClaims(LoginRequestDTO loginRequestDTO, Map<String, Object> additionalClaims, String deviceId, String channel) {
        additionalClaims.put("deviceId", OclDfsPIIEncryption.encrypt(deviceId));
        additionalClaims.put("userName", OclDfsPIIEncryption
                .encrypt(loginRequestDTO.getLoginId()));
        if (StringUtils.equalsAny(channel, "SYSTEM_PORTAL", "RMS_PORTAL")) {
            additionalClaims.put("uniqueTag", request.getAttribute("roles"));
        }
    }

    private @NotNull ClientDetail getDetails() {
        ClientDetail clientDetail = clientDetailsRepository.findByClientKey("client");
        if (ObjectUtils.isEmpty(clientDetail)) {
            logService.printInfoLogs(null, this.getClass().getSimpleName(), "Client Details Not Found");
            throw new ResponseException("Client Details Not Found", ResponseConstant.UNAUTHORIZED_LOGIN);
        }
        return clientDetail;
    }

    private void storeAccessTokenDetails(LoginRequestDTO loginRequestDTO, TokenResponse tokenResponse) {
        AccessToken oauthAccessToken = oauthAccessTokenRepository.findByUsername(loginRequestDTO.getLoginId());

        if (ObjectUtils.isEmpty(oauthAccessToken)) {
            oauthAccessToken = new AccessToken();
        }
        oauthAccessToken.setToken(tokenResponse.getAccessToken());
        oauthAccessToken.setRefreshToken(tokenResponse.getRefreshToken());
        oauthAccessToken.setUsername(loginRequestDTO.getLoginId());
        oauthAccessTokenRepository.save(oauthAccessToken);
    }

}
