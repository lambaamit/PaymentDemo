package com.cca.paymentcore.builder;

import com.cca.paymentcore.constants.ApplicationConstants;
import com.cca.paymentcore.constants.Constants;
import com.cca.paymentcore.dto.responsedto.ResponseDTO;
import com.cca.paymentcore.dto.responsedto.TokenResponseDto;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilderFactory {

    private ResponseBuilderFactory() {
    }

    /**
     * return Response to verify phone number
     *
     * @param message
     * @param responseCode
     * @return ResponseDTO
     */
    public static ResponseDTO getResponse(String message, String responseCode) {
        return ResponseDTO.builder()
                .status(Integer.parseInt(responseCode) >= 20000 ? ApplicationConstants.SUCCESS : ApplicationConstants.FAIL)
                .message(message)
                .responseCode(Integer.parseInt(responseCode))
                .data(null)
                .build();
    }

    /**
     * return Response to verify phone number
     *
     * @param message
     * @param messageCode
     * @param data
     * @return ResponseDTO
     */
    public static ResponseDTO getResponse(String message, int messageCode, Object data) {
        return ResponseDTO.builder()
                .status(messageCode >= 20000 ? ApplicationConstants.SUCCESS : ApplicationConstants.FAIL)
                .message(message)
                .responseCode(messageCode)
                .data(data)
                .build();
    }

    public static ResponseDTO getSuccessResponse(String message, String responseCode) {
        return ResponseDTO.builder()
                .status(Integer.parseInt(responseCode) >= 20000 ? ApplicationConstants.SUCCESS : ApplicationConstants.FAIL)
                .message(message)
                .responseCode(Integer.parseInt(responseCode))
                .data(null)
                .build();
    }

    public static ResponseDTO getSuccessResponse(String message, String responseCode, Object data) {
        return ResponseDTO.builder()
                .status(Integer.parseInt(responseCode) >= 20000 ? ApplicationConstants.SUCCESS : ApplicationConstants.FAIL)
                .message(message)
                .responseCode(Integer.parseInt(responseCode))
                .data(data)
                .build();
    }

    public static ResponseDTO getResponse(String message, int responseCode) {
        return ResponseDTO.builder()
                .status(responseCode >= 20000 ? ApplicationConstants.SUCCESS : ApplicationConstants.FAIL)
                .message(message)
                .responseCode(responseCode)
                .data(null)
                .build();
    }

    /**
     * @param object
     * @param channel
     * @return
     */
    public static ResponseDTO getTokenResponse(JsonNode object, String channel) {
        return ResponseDTO.builder()
                .status(Constants.SUCCESS_STATUS)
                .message(Constants.SUCCESS_STATUS)
                .responseCode(Constants.SUCCESS_CODE)
                .data(getTokenResponseObject(object, channel))
                .build();
    }

    /**
     * @param object
     * @param channel
     * @return
     */
    public static Object getTokenResponseObject(JsonNode object, String channel) {
        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setAccessToken(replaceUnwantedCharacter(String.valueOf(object.get(Constants.ACCESS_TOKEN))));
        tokenResponseDto.setTokenType(replaceUnwantedCharacter(String.valueOf(object.get(Constants.TOKEN_TYPE))));
        tokenResponseDto.setRefreshToken(replaceUnwantedCharacter(String.valueOf(object.get(Constants.REFRESH_TOKEN))));
        tokenResponseDto.setScope(replaceUnwantedCharacter(String.valueOf(object.get(Constants.SCOPE))));
        tokenResponseDto.setExpiresIn(replaceUnwantedCharacter(String.valueOf((object.get(Constants.EXPIRES_IN)))));
        tokenResponseDto.setUsercode(replaceUnwantedCharacter(String.valueOf((object.get(Constants.USER_CODE)))));
        tokenResponseDto.setJti(replaceUnwantedCharacter(String.valueOf(object.get(Constants.JTI))));
//    tokenResponseDto.setUsername(encryptUsername(tokenResponseDto.getUsername(),channel));
        return tokenResponseDto;
    }

    /**
     * @param s
     * @return
     */
    private static String replaceUnwantedCharacter(String s) {
        return s.replace("\"", "");
    }
}
