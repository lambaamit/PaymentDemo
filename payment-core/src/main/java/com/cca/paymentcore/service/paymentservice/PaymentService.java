package com.cca.paymentcore.service.paymentservice;

import com.cca.paymentcore.builder.ResponseBuilderFactory;
import com.cca.paymentcore.constants.ApplicationConstants;
import com.cca.paymentcore.constants.ResponseConstant;
import com.cca.paymentcore.dto.responsedto.ResponseDTO;
import com.cca.paymentcore.dto.transactiondto.TransactionRecord;
import com.cca.paymentcore.entity.carddetails.CardDetails;
import com.cca.paymentcore.entity.transactiondetails.TransactionDetails;
import com.cca.paymentcore.repo.carddetails.CardDetailsRepository;
import com.cca.paymentcore.repo.transactiondetails.TransactionDetailsRepository;
import com.cca.paymentcore.service.loggerservice.LogService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    private final String className = this.getClass().getSimpleName();
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogService logService;
    @Autowired
    private CardDetailsRepository cardDetailsRepository;
    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;


    public ResponseDTO processPayment(TransactionRecord transactionRecord) {
        try {
            logService.printInfoLogs(request, className, "initiatePayment API started processing");

            CardDetails cardDetails = cardDetailsRepository.findByCardNumberAndMerchantId(transactionRecord.cardNumber(), transactionRecord.merchantId());
            if (ObjectUtils.isEmpty(cardDetails)) {
                logService.printResponseErrorLogs(request, className, "Card Details Not Found");
                saveTransactionHistory(transactionRecord, ApplicationConstants.TRANSACTION_FAILED);
                return ResponseBuilderFactory.getResponse(ApplicationConstants.FAIL, ResponseConstant.FAILURE);
            }
            if (ObjectUtils.isEmpty(cardDetails) || cardDetails.getAvailableCardLimit() < transactionRecord.amount()) {
                logService.printResponseErrorLogs(request, className, "Insufficient Balance");
                saveTransactionHistory(transactionRecord, ApplicationConstants.INSUFFICIENT_CREDIT_CARD_BALANCE);
                return ResponseBuilderFactory.getResponse(ApplicationConstants.FAIL, ResponseConstant.FAILURE);
            }
            cardDetails.setAvailableCardLimit(cardDetails.getAvailableCardLimit() - transactionRecord.amount());
            cardDetailsRepository.save(cardDetails);
            saveTransactionHistory(transactionRecord, ApplicationConstants.TRANSACTION_SUCCESSFULL);

            return ResponseBuilderFactory.getResponse(ApplicationConstants.SUCCESS, ResponseConstant.SUCCESS);
        } catch (Exception e) {
            logService.printErrorLogs(request, className, e);
            return ResponseBuilderFactory.getResponse(ApplicationConstants.FAIL, ResponseConstant.FAILURE);
        }

    }

    private void saveTransactionHistory(TransactionRecord transactionRecord, String transactionStatus) {
        TransactionDetails transactionDetails = TransactionDetails.builder()
                .transactionAmount(transactionRecord.amount())
                .merchantId(transactionRecord.merchantId())
                .transactionStatus(transactionStatus)
                .build();
        transactionDetailsRepository.save(transactionDetails);
    }
}
