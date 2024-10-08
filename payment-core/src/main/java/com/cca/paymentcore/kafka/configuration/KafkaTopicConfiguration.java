package com.cca.paymentcore.kafka.configuration;

import com.cca.paymentcore.kafka.constants.KafkaTopicsConstants;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfiguration {

    @Value(value = KafkaTopicsConstants.KAFKA_BOOTSTRAP_SERVER)
    private String brokerIPAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerIPAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic paymentTopic() {
        return new NewTopic(KafkaTopicsConstants.PAYMENT_TOPIC, 10, (short) 1);
    }
}
