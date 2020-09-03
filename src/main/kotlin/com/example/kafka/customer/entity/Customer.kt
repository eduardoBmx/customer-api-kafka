package com.example.kafka.customer.entity

import java.time.LocalDate
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Customer (
    @Id
    val id: String = UUID.randomUUID().toString(),

    val name: String,

    val email: String,

    val startDate: LocalDate
)