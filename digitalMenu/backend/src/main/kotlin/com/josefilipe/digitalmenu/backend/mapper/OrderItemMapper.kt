package com.josefilipe.digitalmenu.backend.mapper

import com.josefilipe.digitalmenu.backend.dto.OrderItemRequestDTO
import com.josefilipe.digitalmenu.backend.dto.OrderItemResponseDTO
import com.josefilipe.digitalmenu.backend.model.OrderItem
import com.josefilipe.digitalmenu.backend.model.Product
import com.josefilipe.digitalmenu.backend.model.CustomerOrder

fun OrderItemRequestDTO.toEntity(customerOrder: CustomerOrder, product: Product) = OrderItem(
    quantity = this.quantity,
    unitPrice = this.unitPrice,
    order = customerOrder,
    product = product
)

fun OrderItem.toResponseDTO() = OrderItemResponseDTO(
    id = this.id,
    quantity = this.quantity,
    unitPrice = this.unitPrice,
    orderId = this.order.id,
    productId = this.product.id
)
