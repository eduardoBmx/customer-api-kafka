package com.example.kafka.customer.kafka.config

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaTopicConfig {

    @Value(value = "\${kafka.bootstrapAddress}")
    lateinit var kafkaAddress: String

    @Value(value = "\${kafka.topicName}")
    lateinit var kafkaTopic: String

    @Bean
    fun kafkaAdmin(): KafkaAdmin = KafkaAdmin(mapOf(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaAddress))

    @Bean
    fun topic() = NewTopic(kafkaTopic,1, 1)
}