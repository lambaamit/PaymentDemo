package com.cca.paymentcore.dto.transactiondto;

public record TransactionRecord(String cardNumber, Double amount, String merchantId) {
}
