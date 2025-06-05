package com.josefilipe.digitalmenu.backend.dto


data class UserRequestDTO(
    val name: String,
    val email: String,
    val password: String,
    val type: String
)

data class UserResponseDTO(
    val id: Long,
    val name: String,
    val email: String,
    val type: String
)
