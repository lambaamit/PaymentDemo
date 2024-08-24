package com.cca.paymentcore.service.validationservice;

import com.cca.paymentcore.constants.ResponseConstant;
import com.cca.paymentcore.exception.ResponseException;
import com.cca.paymentcore.service.loggerservice.LogService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeaderValidationService {

    private final String className = this.getClass().getSimpleName();
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    private LogService logService;

    public void validateHeader() {
        logService.printInfoLogs(httpServletRequest, className, "Validating Headers");
        String channel = httpServletRequest.getHeader("channel");
        if (StringUtils.isBlank(channel)) {
            throw new ResponseException("Channel is missing in the header", ResponseConstant.FAILURE);
        } else if (StringUtils.isBlank(httpServletRequest.getHeader("deviceId"))) {
            throw new ResponseException("DeviceId is missing in the header", ResponseConstant.FAILURE);
        } else if (StringUtils.isBlank(httpServletRequest.getHeader("deviceType"))) {
            throw new ResponseException("DeviceType is missing in the header", ResponseConstant.FAILURE);
        }
    }
}
