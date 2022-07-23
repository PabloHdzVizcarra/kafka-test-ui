package com.pablohdz.kafkatestui

import javafx.scene.control.TextArea
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.util.*


class KafkaClient {
    private lateinit var consumer: Consumer<String, String>

    fun connectKafka(kafkaServersUrls: String, kafkaGroupId: String) {
        consumer = createConsumer(kafkaServersUrls, kafkaGroupId)
    }

    fun startConsumer(topics: List<String>, kafkaProducerOutput: TextArea) {
        val thread = Thread(SimpleRunnable(consumer, topics, kafkaProducerOutput))
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