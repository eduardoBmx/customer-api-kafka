package com.example.kafka.customer.kafka

import com.example.kafka.customer.kafka.entity.Address
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import org.springframework.util.concurrent.ListenableFutureCallback

@Service
class KafkaSend(
    private val kafkaTemplate: KafkaTemplate<String, Address>
): KafkaService {

    @Value(value = "\${kafka.topicName}")
    lateinit var kafkaTopic: String

    override fun sendMessage(address: Address) {
        kafkaTemplate.send(kafkaTopic, address)
            .addCallback(object : ListenableFutureCallback<SendResult<String, Address>> {
                override fun onSuccess(result: SendResult<String, Address>?) {
                    println(
                        "Sent message=[" + address.city +
                                "] with offset=[" + result?.recordMetadata?.offset() + "]"
                    )
                }
                override fun onFailure(ex: Throwable) {
                    println(
                        "Unable to send message=["
                                + address.city + "] due to : " + ex.message
                    )
                }
            })
    }
}
