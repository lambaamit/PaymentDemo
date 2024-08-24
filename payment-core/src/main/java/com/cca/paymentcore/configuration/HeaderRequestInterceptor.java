package com.cca.paymentcore.configuration;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;

public class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        String sessionId = MDC.get("sessionId");
        String operationId = MDC.get("operationId");
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(httpRequest);
        if (StringUtils.isNotEmpty(sessionId)) {
            requestWrapper.getHeaders().set("sessionId", sessionId);
            if (StringUtils.isNotEmpty(operationId)) {
                requestWrapper.getHeaders().set("operationId", operationId);
            }
            return clientHttpRequestExecution.execute(requestWrapper, bytes);
        }
        return clientHttpRequestExecution.execute(requestWrapper, bytes);
    }
}

