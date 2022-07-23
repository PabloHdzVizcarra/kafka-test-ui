package com.pablohdz.kafkatestui

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.control.TextField

class HelloController {
    lateinit var kafkaProducerOutput: TextArea
    lateinit var kafkaProducerInput: TextArea
    lateinit var kafkaTopicsInput: TextField
    lateinit var kafkaGroupIdInput: TextField
    lateinit var kafkaServersInput: TextField
    private lateinit var kafkaClient: KafkaClient



    @FXML
    private lateinit var welcomeText: Label

    @FXML
    private fun onHelloButtonClick() {
        welcomeText.text = "Welcome to JavaFX Application!"

        val kafkaServersUrls = kafkaServersInput.text
        val kafkaGropuId = kafkaGroupIdInput.text
        val kafkaTopics = kafkaTopicsInput.text
        val topicsList = kafkaTopics.split(",").toList()

        kafkaClient = KafkaClient()

        kafkaClient.connectKafka(kafkaServersUrls, kafkaGropuId)

        kafkaClient.startConsumer(topicsList, kafkaProducerOutput)
    }
}