package com.cca.paymentcore.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckSumAesResponseDTO {
    private String checksum;
    private String secretKey;

}
