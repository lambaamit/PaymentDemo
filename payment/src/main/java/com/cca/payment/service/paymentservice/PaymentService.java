package com.cca.payment.service.paymentservice;

import com.cca.payment.builder.ResponseBuilderFactory;
import com.cca.payment.constants.ApplicationConstants;
import com.cca.payment.constants.ResponseConstant;
import com.cca.payment.dto.responsedto.ResponseDTO;
import com.cca.payment.dto.transactiondto.TransactionRecord;
import com.cca.payment.dto.transactiondto.TransactionRecordResponse;
import com.cca.payment.entity.transactiondetails.TransactionDetails;
import com.cca.payment.kafka.constants.KafkaTopicsConstants;
import com.cca.payment.repo.transactiondetails.TransactionDetailsRepository;
import com.cca.payment.service.loggerservice.LogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogService logService;
    private final String className = this.getClass().getSimpleName();
    @Autowired
    private KafkaTemplate<String, TransactionRecord> transactionRecordKafkaTemplate;
    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;


    public ResponseDTO initiatePayment(TransactionRecord transactionRecord) {
        try {
            logService.printInfoLogs(request, className, "initiatePayment API started processing");
            transactionRecordKafkaTemplate.send(KafkaTopicsConstants.PAYMENT_TOPIC, transactionRecord);

            return ResponseBuilderFactory.getResponse(ApplicationConstants.SUCCESS, ResponseConstant.SUCCESS);
        } catch (Exception e) {
            logService.printErrorLogs(request, className, e);
            return ResponseBuilderFactory.getResponse(ApplicationConstants.FAIL, ResponseConstant.FAILURE);
        }

    }


    public ResponseDTO fetchTransaction(String merchantId) {
        try {
            logService.printInfoLogs(request, className, "initiatePayment API started processing");
            List<TransactionRecordResponse> transactionDetails=transactionDetailsRepository.findByMerchantId(merchantId);

            return ResponseBuilderFactory.getResponse(ApplicationConstants.SUCCESS, Integer.parseInt(ResponseConstant.SUCCESS),transactionDetails);
        } catch (Exception e) {
            logService.printErrorLogs(request, className, e);
            return ResponseBuilderFactory.getResponse(ApplicationConstants.FAIL, ResponseConstant.FAILURE);
        }

    }
}
