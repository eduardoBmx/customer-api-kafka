package com.example.kafka.customer.controller

import com.example.kafka.customer.api.CustomerApi
import com.example.kafka.customer.api.request.CustomerRequest
import com.example.kafka.customer.api.response.CustomerResponse
import com.example.kafka.customer.mapper.AddressMapper
import com.example.kafka.customer.service.CustomerService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(
    private val customerService: CustomerService
) : CustomerApi {
    override fun create(
        @RequestBody customerRequest: CustomerRequest
    ): CustomerResponse {
        return customerService.create(customerRequest)
    }

    override fun getAll(): List<CustomerResponse> {
        return customerService.getAll()
    }
}