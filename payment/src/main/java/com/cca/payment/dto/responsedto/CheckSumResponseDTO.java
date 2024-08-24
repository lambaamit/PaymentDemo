package com.cca.payment.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckSumResponseDTO {
    private String checksum;
    private String checkSumSequence;
    private String digitalKey;
    private String secretKey;
    private String body;

}
