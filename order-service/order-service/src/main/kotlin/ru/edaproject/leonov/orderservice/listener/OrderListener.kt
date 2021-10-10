package ru.edaproject.leonov.orderservice.listener

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
import ru.edaproject.leonov.orderservice.model.extension.logger
import ru.edaproject.leonov.orderservice.service.OrderService

@Component
class OrderListener(val orderConsumer: ReactiveKafkaConsumerTemplate<String, OrderCreateRequest>,
                    val orderService: OrderService) : CommandLineRunner {

    private val log = logger()

    @Value("\${topics.order.topic}")
    private lateinit var orderTopic: String

    override fun run(vararg args: String?) {
        createOrderProcess().subscribe()
    }

    private fun createOrderProcess() : Flux<OrderResponse> =
        orderConsumer
                .receiveAutoAck()
                .doOnNext { log.info("topic=${it.topic()} key=${it.key()} value=${it.value()} offset=${it.offset()}") }
                .map { it.value() }
                .flatMap {
                    val request: OrderCreateRequest = it
                    return@flatMap orderService.createOrder(request).asFlux()
                }
                .doOnError{ log.error("Error in send order info: ${it.message}", it) }

}