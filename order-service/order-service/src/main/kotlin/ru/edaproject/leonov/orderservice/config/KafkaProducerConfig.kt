package ru.edaproject.leonov.orderservice.config

import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import reactor.kafka.sender.SenderOptions
import ru.edaproject.leonov.orderservice.model.dto.OrderResponse
import ru.edaproject.leonov.orderservice.model.dto.StoreResponse

@Configuration
class KafkaProducerConfig {

    @Bean
    fun orderProducer(props: KafkaProperties) : ReactiveKafkaProducerTemplate<String, OrderResponse> =
            ReactiveKafkaProducerTemplate(SenderOptions.create(props.buildProducerProperties()))

    @Bean
    fun storeProducer(props: KafkaProperties) : ReactiveKafkaProducerTemplate<String, StoreResponse> =
            ReactiveKafkaProducerTemplate(SenderOptions.create(props.buildProducerProperties()))

}