package com.josefilipe.digitalmenu.backend.service

import com.josefilipe.digitalmenu.backend.dto.AuthenticationResponseDTO
import com.josefilipe.digitalmenu.backend.dto.AuthenticationRequestDTO
import com.josefilipe.digitalmenu.backend.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService (
    private val userRepository: UserRepository,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager
) {
    //authenticate email
    fun authenticate(request: AuthenticationRequestDTO): AuthenticationResponseDTO {
        //autentica o usuario com email e senha
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password)
        )
        // busca o usuario no banco
        val user = userRepository.findByEmail(request.email) ?: throw Exception("Usuário não encontrado")

        //gera o token JWT
        val token = jwtService.generateToken(user)

        //retorna o token
        return AuthenticationResponseDTO(token)
    }
}