package com.cca.paymentcore.exception;

import lombok.*;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class LoggerExceptionDTO {
    String responseCode;
    String message;
    String responseBody;
    String responseStatus;
    String responseType;
    Long processingTime;
}
