package com.cca.payment.dto.transactiondto;

public record TransactionRecord(String cardNumber, Double amount, String merchantId) {
}
