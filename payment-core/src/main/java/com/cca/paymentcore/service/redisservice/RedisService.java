package com.cca.paymentcore.service.redisservice;

import org.springframework.stereotype.Service;

@Service
public interface RedisService {

    void putSessionIdAndParameterValueInMDC(String sessionId, String parameterValue);
}
