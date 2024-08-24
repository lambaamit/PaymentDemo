package com.cca.payment.service.loggerservice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface LogService {

    void printInfoLogs(HttpServletRequest request, String className, String logMessage);

    void printResponseErrorLogs(HttpServletRequest request, String className, String logMessage);

    void printErrorLogs(HttpServletRequest request, String className, Exception exception);

    void printInfoApprovalLogs(
            String approvalCode, String apiName, String className, String logMessage);

    void printErrorApprovalLogs(
            String approvalCode, String apiName, String className, Exception exception);
}
