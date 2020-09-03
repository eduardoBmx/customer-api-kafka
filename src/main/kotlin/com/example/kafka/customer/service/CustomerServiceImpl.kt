package com.example.kafka.customer.service

import com.example.kafka.customer.api.request.CustomerRequest
import com.example.kafka.customer.api.response.AddressResponse
import com.example.kafka.customer.api.response.CustomerResponse
import com.example.kafka.customer.entity.Customer
import com.example.kafka.customer.kafka.KafkaService
import com.example.kafka.customer.kafka.entity.Address
import com.example.kafka.customer.mapper.AddressMapper
import com.example.kafka.customer.repository.CustomerRepository
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.time.LocalDate


@Service
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository,
    private val kafkaService: KafkaService
) : CustomerService {
    override fun create(
        customerRequest: CustomerRequest
    ): CustomerResponse {

        return customerRepository.save(
            Customer(
                name = customerRequest.name,
                email = customerRequest.email,
                startDate = LocalDate.now()
            )
        ).let { customer ->
            kafkaService.sendMessage(customerRequest.toAddress(customer))

            CustomerResponse(
                id = customer.id,
                email = customer.email,
                name = customer.name,
                address = AddressResponse(
                    number = customerRequest.address.number,
                    city = customerRequest.address.city,
                    district = customerRequest.address.district,
                    state = customerRequest.address.state
                )
            )
        }
    }

    override fun getAll(
    ): List<CustomerResponse> {
        val address = getAddressMapper()

        return customerRepository.findAll().map { customer ->
            customer.toResponse(
                address = address.first { it.customerId == customer.id }.toResponse()
            )
        }
    }

    private fun getAddressMapper(): List<AddressMapper> {

        return RestTemplate().getForEntity<Array<AddressMapper>>(
            "http://localhost:8082/address",
            Array<AddressMapper>::class.java
        ).body?.toList() ?: emptyList()

    }

    private fun Customer.toResponse(
        address: AddressResponse
    ) = CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        address = address
    )

    private fun AddressMapper.toResponse() = AddressResponse(
        id = this.id,
        state = this.state,
        district = this.district,
        number = this.number,
        city = this.city
    )

    private fun CustomerRequest.toAddress(customer: Customer) = Address(
        city = this.address.city,
        state = this.address.state,
        district = this.address.district,
        number = this.address.number,
        customerId = customer.id
    )
}