package com.josefilipe.digitalmenu.backend.mapper

import com.josefilipe.digitalmenu.backend.dto.CategoryRequestDTO
import com.josefilipe.digitalmenu.backend.dto.CategoryResponseDTO
import com.josefilipe.digitalmenu.backend.model.Category

fun CategoryRequestDTO.toEntity() = Category(
    name = this.name
)

fun Category.toResponseDTO() = CategoryResponseDTO(
    id = this.id,
    name = this.name
)
