package com.cca.payment.controller.transaction;

import com.cca.payment.dto.responsedto.ResponseDTO;
import com.cca.payment.dto.transactiondto.TransactionRecord;
import com.cca.payment.service.loggerservice.LogService;
import com.cca.payment.service.paymentservice.PaymentService;
import com.cca.payment.service.tokengenerationservice.TokenGenerationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {


    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogService logService;
    private final String className = this.getClass().getSimpleName();
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/initiate-transaction")
    public ResponseDTO initiateTransaction(@RequestBody TransactionRecord transactionRecord) {
        logService.printInfoLogs(request, className, "initiateTransaction API started processing");
       return paymentService.initiatePayment(transactionRecord);
    }

    @PostMapping("/fetch-transaction")
    public ResponseDTO fecthTransaction(@RequestBody String merchantId) {
        logService.printInfoLogs(request, className, "initiateTransaction API started processing");
        return paymentService.fetchTransaction(merchantId);
    }
}
