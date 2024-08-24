package com.cca.payment.kafka.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KafkaTopicsConstants {

    public static final String KAFKA_BOOTSTRAP_SERVER = "${kafka.broker.ip}";
    public static final String SAMPLE_TOPIC = "SAMPLE_TOPIC";

    public static final String PAYMENT_TOPIC = "PAYMENT_TOPIC";
}
