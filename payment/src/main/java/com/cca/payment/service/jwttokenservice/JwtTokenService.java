package com.cca.payment.service.jwttokenservice;

import com.cca.payment.builder.ResponseBuilderFactory;
import com.cca.payment.constants.Constants;
import com.cca.payment.constants.ResponseConstant;
import com.cca.payment.dto.TokenResponse;
import com.cca.payment.dto.responsedto.ResponseDTO;
import com.cca.payment.encryption.OclDfsPIIEncryption;
import com.cca.payment.entity.clientdetail.ClientDetail;
import com.cca.payment.entity.oauth.AccessToken;
import com.cca.payment.entity.userauth.UserAuth;
import com.cca.payment.exception.ResponseException;
import com.cca.payment.repo.ClientDetailRepo.ClientDetailsRepository;
import com.cca.payment.repo.oauth.OauthAccessTokenRepository;
import com.cca.payment.repo.userauth.UserAuthRepository;
import com.cca.payment.service.loggerservice.LogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenService {

    @Value("${jwt.access.private.key}")
    private String accessPrivateKeyString;

    @Value("${jwt.access.public.key}")
    private String accessPublicKeyString;

    @Value("${jwt.refresh.private.key}")
    private String refreshPrivateKeyString;

    @Value("${jwt.refresh.public.key}")
    private String refreshPublicKeyString;

    private KeyPair accessKeyPair;
    private KeyPair refreshKeyPair;
    @Autowired
    private LogService logService;
    @Autowired
    private HttpServletRequest request;
    private final String className = this.getClass().getSimpleName();
    @Autowired
    private OauthAccessTokenRepository oauthAccessTokenRepository;
    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private ClientDetailsRepository clientDetailsRepository;


    private PrivateKey getPrivateKey(String keyString) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(keyString);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    private PublicKey getPublicKey(String keyString) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(keyString);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public TokenResponse generateToken(String subject, Map<String, Object> additionalClaims, Long accessTokenValidity, Long refreshTokenValidity) throws Exception {
        Map<String, Object> claims = new HashMap<>(additionalClaims);
        accessKeyPair = new KeyPair(getPublicKey(accessPublicKeyString), getPrivateKey(accessPrivateKeyString));
        refreshKeyPair = new KeyPair(getPublicKey(refreshPublicKeyString), getPrivateKey(refreshPrivateKeyString));

        // Calculate access token expiration
        Date expirationDate = new Date(System.currentTimeMillis() + accessTokenValidity * 1000);
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(accessKeyPair.getPrivate(), SignatureAlgorithm.RS512)
                .compact();
        long expirationInSeconds = accessTokenValidity;

        // Generate refresh token
        Date refreshExpirationDate = new Date(System.currentTimeMillis() + refreshTokenValidity * 1000);
        String refreshToken = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(refreshExpirationDate)
                .signWith(refreshKeyPair.getPrivate(), SignatureAlgorithm.RS512)
                .compact();
        long refreshExpirationInSeconds = refreshTokenValidity;

        return new TokenResponse(token, String.valueOf(expirationInSeconds), refreshToken);
    }


    public String verifyToken(String token) {
        try {
            return getTokenResponse(accessPublicKeyString, token);
        } catch (ExpiredJwtException e) {
            throw new ResponseException(Constants.APP_SESSION_EXPIRED, ResponseConstant.APP_SESSION_EXPIRED_CODE); // Token is expired
        } catch (ResponseException responseException) {
            logService.printErrorLogs(request, className, responseException);
            throw responseException;
        } catch (SignatureException e) {
            logService.printErrorLogs(request, className, e);
            return Constants.FAILURE_STATUS; // Invalid signature
        } catch (Exception e) {
            logService.printErrorLogs(request, className, e);
            return Constants.FAILURE_STATUS; // Other errors
        }
    }


    public String getNameFromToken(String token) {
        String userName = "";
        try {
            String[] splitString = token.split("\\.");
            String base64EncodedBody = splitString[1];
            org.apache.commons.codec.binary.Base64 base64Url = new org.apache.commons.codec.binary.Base64(true);
            String payload = new String(base64Url.decode(base64EncodedBody));
            // convert JSON string to Map
            Date currentDate = new Date(System.currentTimeMillis());
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> payloadDataMap = mapper.readValue(payload, Map.class);
            userName = (String) payloadDataMap.get("userName");
        } catch (Exception e) {
            logService.printErrorLogs(
                    request, className, e);
        }
        return userName;
    }

    public ResponseDTO refreshToken(String token) {
        String username = StringUtils.EMPTY;
        try {
            var tokenResponse = verifyRefreshToken(token);
            if (StringUtils.isNotBlank(tokenResponse) && !StringUtils.equalsIgnoreCase(tokenResponse, Constants.FAILURE_STATUS)) {
                username = tokenResponse;
                AccessToken oauthAccessToken = oauthAccessTokenRepository.findByUsername(tokenResponse);
                if (ObjectUtils.isEmpty(oauthAccessToken) || !StringUtils.equalsIgnoreCase(oauthAccessToken.getRefreshToken(), token)) {
                    throw new ResponseException(Constants.INVALID_TOKEN_1, ResponseConstant.TOKEN_INVALID);
                }
                var accessTokenResponse = verifyToken(oauthAccessToken.getToken());
                if (StringUtils.isNotBlank(accessTokenResponse) && !StringUtils.equalsIgnoreCase(accessTokenResponse, Constants.FAILURE_STATUS)) {
                    TokenResponse tokenResponse1 = new TokenResponse(oauthAccessToken.getToken()
                            , String.valueOf(getTimeRemainingFromToken(oauthAccessToken.getToken())),
                            oauthAccessToken.getRefreshToken());
                    return ResponseBuilderFactory.getResponse(Constants.SUCCESS_STATUS, Integer.parseInt(ResponseConstant.SUCCESS), tokenResponse1);
                }
            }
        } catch (ResponseException responseException) {
            return getRefreshTokenResponse(responseException, username);
        } catch (Exception e) {
            logService.printErrorLogs(request, className, e);
        }
        return ResponseBuilderFactory.getResponse(Constants.INVALID_SESSION, ResponseConstant.TOKEN_INVALID);
    }


    public String verifyRefreshToken(String token) {
        try {
            return getTokenResponse(refreshPublicKeyString, token);
        } catch (ExpiredJwtException e) {
            throw new ResponseException(Constants.APP_SESSION_EXPIRED, ResponseConstant.REFRESH_TOKEN_EXPIRED); // Refresh Token is expired
        } catch (ResponseException responseException) {
            logService.printErrorLogs(request, className, responseException);
            throw responseException;
        } catch (SignatureException e) {
            logService.printErrorLogs(request, className, e);
            return Constants.FAILURE_STATUS; // Invalid signature
        } catch (Exception e) {
            logService.printErrorLogs(request, className, e);
            return Constants.FAILURE_STATUS; // Other errors
        }
    }

    private String getTokenResponse(String publicKeyString, String token) throws Exception {
        PublicKey publicKey = getPublicKey(publicKeyString);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }


    private @NotNull ClientDetail getDetails() {
        ClientDetail clientDetail = clientDetailsRepository.findByClientKey("client");
        if (ObjectUtils.isEmpty(clientDetail)) {
            logService.printInfoLogs(null, this.getClass().getSimpleName(), "Client Details Not Found");
            throw new ResponseException("Client Details Not Found", ResponseConstant.UNAUTHORIZED_LOGIN);
        }
        return clientDetail;
    }

    private void storeAccessTokenDetails(String username, TokenResponse tokenResponse) {
        AccessToken oauthAccessToken = oauthAccessTokenRepository.findByUsername(username);
        if (ObjectUtils.isEmpty(oauthAccessToken)) {
            oauthAccessToken = new AccessToken();
        }
        oauthAccessToken.setToken(tokenResponse.getAccessToken());
        oauthAccessToken.setRefreshToken(tokenResponse.getRefreshToken());
        oauthAccessToken.setUsername(username);
        oauthAccessTokenRepository.save(oauthAccessToken);
    }

    private void updateClaims(String username, Map<String, Object> additionalClaims, String deviceId, String channel) {
        additionalClaims.put("deviceId", OclDfsPIIEncryption.encrypt(deviceId));
        additionalClaims.put("userName", OclDfsPIIEncryption
                .encrypt(username));
    }


    public ResponseDTO getRefreshTokenResponse(ResponseException responseException, String username) {
        logService.printResponseErrorLogs(request, className, responseException.getMessage());
        switch (responseException.getResponseCode()) {
            case ResponseConstant.APP_SESSION_EXPIRED_CODE -> {
                UserAuth userAuth = userAuthRepository.findByUsername(username);
                if (ObjectUtils.isEmpty(userAuth)) {
                    return ResponseBuilderFactory.getResponse(Constants.INVALID_TOKEN_1, ResponseConstant.TOKEN_INVALID);
                }
                ClientDetail clientDetail = getDetails();
                Map<String, Object> additionalClaims = new HashMap<>();
                updateClaims(username, additionalClaims, userAuth.getDeviceIid(), userAuth.getChannel());
                try {
                    // Generate Token
                    TokenResponse tokenResponse = generateToken(username, additionalClaims
                            , clientDetail.getAccessTokenValidityInSeconds(), clientDetail.getRefreshTokenValidityInSeconds());
                    logService.printInfoLogs(null, this.getClass().getSimpleName(), "Token Generated Successfully");
                    storeAccessTokenDetails(username, tokenResponse);
                } catch (Exception e) {
                    logService.printErrorLogs(request, className, e);
                    return ResponseBuilderFactory.getResponse(Constants.FAILURE_STATUS, ResponseConstant.FAILURE);
                }
            }
            case ResponseConstant.TOKEN_INVALID -> {
                return ResponseBuilderFactory.getResponse(Constants.INVALID_TOKEN_1, ResponseConstant.TOKEN_INVALID);
            }
            case ResponseConstant.REFRESH_TOKEN_EXPIRED -> {
                return ResponseBuilderFactory.getResponse(Constants.INVALID_SESSION, ResponseConstant.REFRESH_TOKEN_EXPIRED);
            }
            default -> {
                return ResponseBuilderFactory.getResponse(Constants.FAILURE_STATUS, ResponseConstant.FAILURE);
            }
        }
        return ResponseBuilderFactory.getResponse(Constants.FAILURE_STATUS, ResponseConstant.FAILURE);
    }

    public long getTimeRemainingFromToken(String token) {
        try {
            PublicKey publicKey = getPublicKey(accessPublicKeyString);
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Date expirationDate = claims.getExpiration();
            long currentTimeInMillis = System.currentTimeMillis();
            long expirationTimeInMillis = expirationDate.getTime();

            return (expirationTimeInMillis - currentTimeInMillis) / 1000; // Time remaining in seconds
        } catch (Exception e) {
            logService.printErrorLogs(request, className, e);
            return -1;
        }
    }
}