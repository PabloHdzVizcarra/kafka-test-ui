package com.pablohdz.kafkatestui


import javafx.scene.control.TextArea
import org.apache.kafka.clients.consumer.Consumer
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*


class SimpleRunnable(private val consumer: Consumer<String, String>,
                     private val topics: List<String>,
                     private val producerOutput: TextArea
): Runnable {
    override fun run() {
        consumer.use {
            consumer.subscribe(topics)

            while (true) {
                val records = consumer.poll(Duration.ofSeconds(1))
                println("records fetched: ${records.count()}")

                records.iterator().forEach {
                    val message = it.value()
                    val topic = it.topic()
                    val timestamp = it.timestamp()
                    val date = Date(timestamp)
                    val format = SimpleDateFormat("dd-MM-yy HH:mm:ss")
                    val timeFormatter = format.format(date)

                    val logFormatted = "Message TIME: $timeFormatter ||||| from  TOPIC: $topic ||||| MESSAGE:$message \n"

                    producerOutput.appendText(logFormatted)
                    println(logFormatted)
                }
            }
        }
    }
}