package com.productos.productoservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productos.productoservice.dto.ProductKafkaDto;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // ── Producer ──────────────────────────────────────────────────────────────

    @Bean
    public ProducerFactory<String, ProductKafkaDto> producerFactory() {
        ObjectMapper mapper = new ObjectMapper();
        JsonSerializer<ProductKafkaDto> serializer = new JsonSerializer<>(mapper);
        serializer.setAddTypeInfo(false); // Mantiene el JSON limpio sin metadatos de Java

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // Aquí le decimos a Kafka cómo convertir la llave (String) y el valor (Nuestro DTO) a bytes
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(props, new StringSerializer(), serializer);
    }

    @Bean
    public KafkaTemplate<String, ProductKafkaDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}