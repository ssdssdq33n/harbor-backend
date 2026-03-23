package com.hungnv.backend.infrastructure.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@ConditionalOnProperty(prefix = "app.kafka", name = "enabled", havingValue = "true")
public class KafkaEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final Environment environment;

    public KafkaEventProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper, Environment environment) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.environment = environment;
    }

    public void publishYardEvent(Map<String, Object> payload) {
        String topic = environment.getProperty("app.kafka.topic.yard-events", "yard-events");
        try {
            kafkaTemplate.send(topic, objectMapper.writeValueAsString(payload));
        } catch (Exception ignored) {
        }
    }
}

