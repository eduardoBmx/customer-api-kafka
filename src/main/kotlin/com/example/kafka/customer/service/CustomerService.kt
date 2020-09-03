package com.example.kafka.customer.service

import com.example.kafka.customer.api.request.CustomerRequest
import com.example.kafka.customer.api.response.CustomerResponse

interface CustomerService {

    fun create(
        customerRequest: CustomerRequest
    ) : CustomerResponse

    fun getAll(): List<CustomerResponse>
}