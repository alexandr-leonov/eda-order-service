package ru.edaproject.leonov.orderservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate
import reactor.kafka.receiver.ReceiverOptions
import ru.edaproject.leonov.orderservice.model.dto.OrderCreateRequest
import java.util.*

@Configuration
class KafkaConsumerConfig {

    @Value("\${topics.order.createTopic}")
    private lateinit var orderCreateTopic : String

    @Bean
    fun orderConsumer(props: KafkaProperties) : ReactiveKafkaConsumerTemplate<String, OrderCreateRequest> {
        val options: ReceiverOptions<String, OrderCreateRequest> = ReceiverOptions
                .create(props.buildConsumerProperties())
        options.subscription(Collections.singletonList(orderCreateTopic))
        return ReactiveKafkaConsumerTemplate(options)
    }


}