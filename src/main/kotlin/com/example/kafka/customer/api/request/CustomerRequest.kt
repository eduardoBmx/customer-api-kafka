package com.example.kafka.customer.api.request

data class CustomerRequest(
    val name: String,

    val email: String,

    val address: AddressRequest
)

data class AddressRequest(
    val city: String,

    val district: String,

    val state: String,

    val number: String
)