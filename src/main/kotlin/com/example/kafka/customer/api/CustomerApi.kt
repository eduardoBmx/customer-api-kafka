package com.example.kafka.customer.api

import com.example.kafka.customer.api.request.CustomerRequest
import com.example.kafka.customer.api.response.CustomerResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus

@RequestMapping("/customer")
interface CustomerApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody customerRequest: CustomerRequest
    ): CustomerResponse

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll(): List<CustomerResponse>

}