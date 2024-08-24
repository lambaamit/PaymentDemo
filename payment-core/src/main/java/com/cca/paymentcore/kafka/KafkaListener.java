package com.cca.paymentcore.kafka;

import com.cca.paymentcore.dto.transactiondto.TransactionRecord;
import com.cca.paymentcore.kafka.constants.KafkaTopicsConstants;
import com.cca.paymentcore.service.loggerservice.LogService;
import com.cca.paymentcore.service.paymentservice.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaListener {

    @Autowired
    private LogService logService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private PaymentService paymentService;


    @org.springframework.kafka.annotation.KafkaListener(
            topics = KafkaTopicsConstants.PAYMENT_TOPIC,
            groupId = KafkaTopicsConstants.PAYMENT_TOPIC,
            containerFactory = "kafkaPaymentListenerContainerFactory")
    public void listenPaymentTopic(TransactionRecord transactionRecord) {
        try {
            paymentService.processPayment(transactionRecord);

        } catch (Exception e) {
            logService.printErrorLogs(request, this.getClass().getSimpleName(), e);
        } finally {
            MDC.clear();
        }
    }
}
