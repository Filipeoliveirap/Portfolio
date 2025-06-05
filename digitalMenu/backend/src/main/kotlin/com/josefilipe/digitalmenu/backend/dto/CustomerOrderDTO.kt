package com.josefilipe.digitalmenu.backend.dto

import java.time.LocalDateTime

data class CustomerRequestDTO(
    val customerName: String,
    val status: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val total: Double = 0.0
)

data class CustomerResponseDTO(
    val id: Long,
    val customerName: String,
    val status: String,
    val createdAt: LocalDateTime,
    val total: Double
)
