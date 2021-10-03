package ru.edaproject.leonov.orderservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaConfig {
    @Value("\${kafka.consumer.topic}")
    private lateinit var topic: String;


}