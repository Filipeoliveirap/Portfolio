package com.josefilipe.digitalmenu.backend.dto

data class AuthenticationRequestDTO (
    val email: String,
    val password: String
)

data class AuthenticationResponseDTO (
    val token: String
)