package com.pablohdz.kafkatestui

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import org.apache.kafka.clients.producer.ProducerRecord

class HelloController {
    lateinit var topicProducerInput: TextField
    lateinit var buttonSendKafkaNotification: Button
    lateinit var buttonConnectKafka: Button
    lateinit var kafkaProducerOutput: TextArea
    lateinit var kafkaProducerInput: TextArea
    lateinit var kafkaTopicsInput: TextField
    lateinit var kafkaGroupIdInput: TextField
    lateinit var kafkaServersInput: TextField
    private lateinit var kafkaClient: KafkaClient
    private lateinit var kafkaServersUrls: String



    @FXML
    private lateinit var welcomeText: Label

    @FXML
    private fun onHelloButtonClick() {
        welcomeText.text = "Welcome to JavaFX Application!"

        disableButton()

        kafkaServersUrls = kafkaServersInput.text
        val kafkaGropuId = kafkaGroupIdInput.text
        val kafkaTopics = kafkaTopicsInput.text
        val topicsList = kafkaTopics.split(",").toList()

        disableTextInput()

        kafkaClient = KafkaClient()
        kafkaClient.connectKafka(kafkaServersUrls, kafkaGropuId)
        kafkaClient.startConsumer(topicsList, kafkaProducerOutput)
    }

    private fun disableButton() {
        buttonConnectKafka.text = "Connected to Kafka Server"
        buttonConnectKafka.isDisable = true
    }

    private fun disableTextInput() {
        kafkaTopicsInput.isDisable = true
        kafkaServersInput.isDisable = true
        kafkaGroupIdInput.isDisable = true
    }

    @FXML
    private fun onSendNotificationButton() {
        println("Send notification")
        val producer = kafkaClient.createKafkaProducer(kafkaServersUrls)
        val topic = topicProducerInput.text
        val message = kafkaProducerInput.text

        val future = producer.send(ProducerRecord(topic, "example-key", message))
        future.get()
    }
}