package com.cca.payment.kafka.configuration;

import com.cca.payment.dto.transactiondto.TransactionRecord;
import com.cca.payment.kafka.constants.KafkaTopicsConstants;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaCommunicationMessageProducer {

    @Value(value = KafkaTopicsConstants.KAFKA_BOOTSTRAP_SERVER)
    private String brokerIPAddress;

    @Bean
    public ProducerFactory<String, TransactionRecord> transactionProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerIPAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, TransactionRecord> transactionRecordKafkaTemplate() {
        return new KafkaTemplate<>(transactionProducerFactory());
    }
}
