package com.example.kafka.customer.mapper


data class AddressMapper(

    val id: String,

    val city: String,

    val district: String,

    val state: String,

    val number: String,

    val customerId: String
)