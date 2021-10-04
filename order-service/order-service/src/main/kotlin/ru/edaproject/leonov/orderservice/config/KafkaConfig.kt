package ru.edaproject.leonov.orderservice.config

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.processor.AbstractProcessor
import org.apache.kafka.streams.processor.ProcessorSupplier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.util.*

@Configuration
class KafkaConfig {
    @Value("\${kafka.bootstrap.servers}")
    private lateinit var servers: String
    @Value("\${kafka.consumer.groupId}")
    private lateinit var consumerGroup: String
    @Value("\${kafka.producer.clientId}")
    private lateinit var clientId: String
    @Value("\${kafka.streams.applicationId}")
    private lateinit var applicationId: String
    @Value("\${kafka.streams.sourceTopic}")
    private lateinit var sourceTopic: String
    @Value("\${kafka.streams.sinkTopic}")
    private lateinit var sinkTopic: String

    @Bean
    fun kafkaConsumer() : KafkaConsumer<String, String> {
        val props = Properties()
        props.put("bootstrap.servers", servers)
        props.put("group.id", consumerGroup)
        props.put("key.deserializer", StringDeserializer::class.java.getName())
        props.put("value.deserializer", StringDeserializer::class.java.getName())
        return KafkaConsumer<String, String>(props)
    }

    @Bean
    fun kafkaProducer() : KafkaProducer<String, String> {
        val props = Properties()
        props.put("bootstrap.servers", servers)
        props.put("client.id", clientId)
        props.put("key.serializer", StringSerializer::class.java.getName())
        props.put("value.serializer", StringSerializer::class.java.getName())
        return KafkaProducer<String, String>(props)
    }

    @Bean
    fun kafkaStreams() : KafkaStreams {
        val props = Properties()
        props.put("bootstrap.servers", servers)
        props.put("application.id", applicationId)

        val topology = Topology()
        topology.addSource("Source", sourceTopic)
                .addProcessor("Proc",  ProcessorSupplier { ConversionProcessor() }, "Source")
                .addSink("Sink", sinkTopic, "Proc")

        return KafkaStreams(topology, props)
    }
}

@Component
class ConversionProcessor : AbstractProcessor<String, String>() {
    override fun process(key: String?, value: String?) {
        var newValue = "Value $value"
        context.forward(key, newValue)
        context.commit()
    }
}