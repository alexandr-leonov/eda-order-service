package ru.edaproject.leonov.orderservice.handler

import kotlinx.coroutines.reactor.asFlux
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.kafka.sender.SenderResult
import ru.edaproject.leonov.orderservice.model.dto.OrderCreateRequest
import ru.edaproject.leonov.orderservice.model.dto.OrderResponse
import ru.edaproject.leonov.orderservice.service.OrderService

// TODO move all print() to slf4j with logback config
@Component
class OrderHandler(val orderConsumer: ReactiveKafkaConsumerTemplate<String, OrderCreateRequest>,
                   val orderProducer: ReactiveKafkaProducerTemplate<String, OrderResponse>,
                   val orderService: OrderService) : CommandLineRunner {

    @Value("\${topics.order.topic}")
    private lateinit var orderTopic: String

    override fun run(vararg args: String?) {
        createOrderProcess().subscribe()
    }

    private fun createOrderProcess() : Flux<SenderResult<Void>> =
        orderConsumer
                .receiveAutoAck()
                .doOnNext { print("topic=${it.topic()} key=${it.key()} value=${it.value()} offset=${it.offset()}") }
                .map { it.value() }
                .flatMap {
                    val request: OrderCreateRequest = it
                    return@flatMap orderService.createOrder(request).asFlux()
                }
                // TODO move from queue to REST endpoints
                .flatMap {
                    val order: OrderResponse = it
                    return@flatMap orderProducer.send(orderTopic, order)
                }
                .doOnError{ print("Error in send order info: ${it.message}") }

}