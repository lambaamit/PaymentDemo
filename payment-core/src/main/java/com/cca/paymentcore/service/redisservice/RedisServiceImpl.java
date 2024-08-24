package com.cca.paymentcore.service.redisservice;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@SuppressWarnings("java:S6813")
public class RedisServiceImpl implements RedisService {

    public void putSessionIdAndParameterValueInMDC(String sessionId, String parameterValue) {
        MDC.put("sessionId", sessionId);
        MDC.put("uniqueIdentifier", parameterValue);
    }
}
