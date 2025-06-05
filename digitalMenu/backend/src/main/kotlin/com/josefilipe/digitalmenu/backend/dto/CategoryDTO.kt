package com.josefilipe.digitalmenu.backend.dto

data class CategoryRequestDTO(
    val name: String

)

data class CategoryResponseDTO(
    val id: Long,
    val name: String
)