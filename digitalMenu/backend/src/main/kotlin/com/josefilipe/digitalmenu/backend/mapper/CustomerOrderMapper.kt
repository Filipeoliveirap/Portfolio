package com.josefilipe.digitalmenu.backend.mapper

import com.josefilipe.digitalmenu.backend.dto.CustomerRequestDTO
import com.josefilipe.digitalmenu.backend.dto.CustomerResponseDTO
import com.josefilipe.digitalmenu.backend.model.CustomerOrder

fun CustomerRequestDTO.toEntity() = CustomerOrder(
    customerName = this.customerName,
    status = this.status,
    createdAt = this.createdAt,
    total = this.total
)

fun CustomerOrder.toResponseDTO() = CustomerResponseDTO(
    id = this.id,
    customerName = this.customerName,
    status = this.status,
    createdAt = this.createdAt,
    total = this.total
)
