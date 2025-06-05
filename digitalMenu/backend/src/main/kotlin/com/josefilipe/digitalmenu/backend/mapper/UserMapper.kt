package com.josefilipe.digitalmenu.backend.mapper

import com.josefilipe.digitalmenu.backend.dto.UserRequestDTO
import com.josefilipe.digitalmenu.backend.dto.UserResponseDTO
import com.josefilipe.digitalmenu.backend.model.User


fun UserRequestDTO.toEntity() = User(
    name = this.name,
    email = this.email,
    password = this.password,
    type = this.type
)


fun User.toResponseDTO() = UserResponseDTO(
    id = this.id,
    name = this.name,
    email = this.email,
    type = this.type
)