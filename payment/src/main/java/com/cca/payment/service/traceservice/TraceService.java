package com.cca.payment.service.traceservice;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanContext;
import org.springframework.stereotype.Service;

@Service
public class TraceService {

    public String getCurrentTraceId() {
        Span currentSpan = Span.current();
        SpanContext spanContext = currentSpan.getSpanContext();

        if (spanContext.isValid()) {
            return spanContext.getTraceId();
        }

        return null;
    }
}
