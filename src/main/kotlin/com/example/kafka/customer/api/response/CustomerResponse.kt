package com.example.kafka.customer.api.response

data class CustomerResponse (
    val id: String,

    val name: String,

    val email: String,

    val address: AddressResponse
)

data class AddressResponse(
    val id: String? = null,

    val city: String,

    val district: String,

    val state: String,

    val number: String
)