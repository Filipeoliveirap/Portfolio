package com.josefilipe.digitalmenu.backend.dto

data class ProductRequestDTO(
    val name: String,
    val description: String?,
    val price: Double,
    val categoryId: Long,
    val imageUrl: String?
)

data class ProductResponseDTO(
    val id: Long,
    val name: String,
    val description: String?,
    val price: Double,
    val categoryName: String,
    val imageUrl: String?
)