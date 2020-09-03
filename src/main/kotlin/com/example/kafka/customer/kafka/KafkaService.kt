package com.example.kafka.customer.kafka

import com.example.kafka.customer.kafka.entity.Address

interface KafkaService {

    fun sendMessage(address: Address)
}