package ru.edaproject.leonov.orderservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

//import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@EnableWebFlux
@SpringBootApplication
//@EnableR2dbcRepositories
class OrderServiceApplication

fun main(args: Array<String>) {
	runApplication<OrderServiceApplication>(*args)
}
