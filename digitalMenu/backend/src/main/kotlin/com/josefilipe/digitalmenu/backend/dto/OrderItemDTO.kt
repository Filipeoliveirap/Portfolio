package com.josefilipe.digitalmenu.backend.dto

data class OrderItemRequestDTO(
    val quantity: Int,
    val unitPrice: Double = 0.0,
    val orderId: Long,
    val productId: Long
)

data class OrderItemResponseDTO(
    val id: Long,
    val quantity: Int,
    val unitPrice: Double = 0.0,
    val orderId: Long,
    val productId: Long
)
