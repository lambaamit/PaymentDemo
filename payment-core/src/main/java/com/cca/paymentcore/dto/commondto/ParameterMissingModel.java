package com.cca.paymentcore.dto.commondto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParameterMissingModel {

    private String missingKey;
    private String message;
    private String status;
    private Integer responseCode;

}
