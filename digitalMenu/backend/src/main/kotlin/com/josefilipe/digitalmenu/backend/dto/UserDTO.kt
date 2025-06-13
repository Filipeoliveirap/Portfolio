package com.josefilipe.digitalmenu.backend.dto
import com.josefilipe.digitalmenu.backend.model.UserType

data class UserRequestDTO(
    val name: String,
    val email: String,
    val password: String,
    val type: UserType
)

data class UserResponseDTO(
    val id: Long,
    val name: String,
    val email: String,
    val type: UserType
)
