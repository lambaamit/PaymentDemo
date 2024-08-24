package com.cca.payment.dto.logindto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SapCdcCommonResponseDTO {

    private Integer apiVersion;
    private String callId;
    private Integer errorCode;
    private String errorDetails;
    private String errorMessage;
    private String fullEventName;
    private String time;
    private Integer statusCode;
    private String statusReason;

}
