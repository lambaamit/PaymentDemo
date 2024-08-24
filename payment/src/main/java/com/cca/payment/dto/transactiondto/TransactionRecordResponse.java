package com.cca.payment.dto.transactiondto;

import java.sql.Timestamp;

public record TransactionRecordResponse(String merchantId, String transactionId,
                                        Timestamp createdAt,double transactionAmount,String transactionStatus) {
}
