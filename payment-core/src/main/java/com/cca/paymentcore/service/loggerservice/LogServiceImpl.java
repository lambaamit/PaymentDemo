package com.cca.paymentcore.service.loggerservice;

import com.cca.paymentcore.constants.ApplicationConstants;
import com.cca.paymentcore.utility.Utility;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Level;

@Slf4j
@Service
public class LogServiceImpl implements LogService {

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public void printInfoLogs(HttpServletRequest request, String className, String logMessage) {
        log.info(
                ApplicationConstants.LOG_NEW_PREFIX,
                Utility.getCurrentTimeStampObject(),
                Level.INFO,
                Utility.getIp(),
                getRequestID(request),
                null,
                appName,
                null,
                getRequestUri(request),
                className,
                logMessage);
    }

    @Override
    public void printResponseErrorLogs(
            HttpServletRequest request, String className, String logMessage) {
        log.error(
                ApplicationConstants.LOG_NEW_PREFIX,
                Utility.getCurrentTimeStampObject(),
                ApplicationConstants.ERROR.toUpperCase(),
                Utility.getIp(),
                getRequestID(request),
                null,
                appName,
                null,
                getRequestUri(request),
                className,
                logMessage);
    }

    @Override
    public void printErrorLogs(HttpServletRequest request, String className, Exception exception) {
        log.error(
                ApplicationConstants.ERROR_LOG_PREFIX,
                Utility.getCurrentTimeStampObject(),
                ApplicationConstants.ERROR.toUpperCase(),
                Utility.getIp(),
                getRequestID(request),
                null,
                appName,
                null,
                getRequestUri(request),
                className,
                ExceptionUtils.getStackTrace(exception));
    }

    @Override
    public void printInfoApprovalLogs(
            String approvalCode, String requestType, String className, String logMessage) {
        log.info(
                ApplicationConstants.LOG_APPROVAL_PREFIX,
                Utility.getCurrentTimeStampObject(),
                Level.INFO,
                Utility.getIp(),
                StringUtils.defaultIfBlank(approvalCode, StringUtils.EMPTY),
                null,
                appName,
                null,
                StringUtils.defaultIfBlank(requestType, StringUtils.EMPTY),
                className,
                logMessage);
    }

    @Override
    public void printErrorApprovalLogs(
            String approvalCode, String requestType, String className, Exception exception) {
        log.error(
                ApplicationConstants.ERROR_LOG_APPROVAL_PREFIX,
                Utility.getCurrentTimeStampObject(),
                ApplicationConstants.ERROR.toUpperCase(),
                Utility.getIp(),
                StringUtils.defaultIfBlank(approvalCode, StringUtils.EMPTY),
                null,
                appName,
                null,
                StringUtils.defaultIfBlank(requestType, StringUtils.EMPTY),
                className,
                ExceptionUtils.getStackTrace(exception));
    }

    private Long getRequestID(HttpServletRequest request) {
        if (request != null) {
            try {
                return Utility.getRequestId(request);
            } catch (IllegalStateException e) {
                // Handle the case where there's no thread-bound request context
                return Utility.getRequestId(); // Provide a default value for async requests
            }
        }
        return Utility.getRequestId(); // Provide a default value for async requests
    }

    private String getRequestUri(HttpServletRequest request) {
        if (request != null) {
            try {
                return request.getRequestURI();
            } catch (IllegalStateException e) {
                // Handle the case where there's no thread-bound request context
                return StringUtils.EMPTY; // Provide a default value for async requests
            }
        }
        return StringUtils.EMPTY; // Provide a default value for async requests
    }
}
