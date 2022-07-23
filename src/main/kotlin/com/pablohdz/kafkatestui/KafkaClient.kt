package com.pablohdz.kafkatestui

import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.util.*


class KafkaClient {
    private lateinit var consumer: Consumer<String, String>

    fun connectKafka(kafkaServersUrls: String, kafkaGroupId: String) {
        consumer = createConsumer(kafkaServersUrls, kafkaGroupId)
    }

    fun startConsumer(topics: List<String>) {
        val thread = Thread(SimpleRunnable(consumer, topics))
        thread.start()
    }

    private fun createConsumer(kafkaServersUrls: String, kafkaGroupId: String): Consumer<String, String> {
        val props = Properties()
        props["bootstrap.servers"] = kafkaServersUrls
        props["group.id"] = kafkaGroupId
        props["key.deserializer"] = StringDeserializer::class.java
        props["value.deserializer"] = StringDeserializer::class.java
        return KafkaConsumer(props)
    }
}