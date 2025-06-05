package com.josefilipe.digitalmenu.backend.mapper

import com.josefilipe.digitalmenu.backend.dto.ProductRequestDTO
import com.josefilipe.digitalmenu.backend.dto.ProductResponseDTO
import com.josefilipe.digitalmenu.backend.model.Product
import com.josefilipe.digitalmenu.backend.model.Category

fun ProductRequestDTO.toEntity(category: Category) = Product(
    name = this.name,
    description = this.description,
    price = this.price,
    category = category,
    imageUrl = this.imageUrl
)

fun Product.toResponseDTO() = ProductResponseDTO(
    id = this.id,
    name = this.name,
    description = this.description,
    price = this.price,
    categoryName = this.category.name,
    imageUrl = this.imageUrl
)

