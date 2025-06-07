package com.josefilipe.digitalmenu.backend.service

import com.josefilipe.digitalmenu.backend.dto.UserRequestDTO
import com.josefilipe.digitalmenu.backend.dto.UserResponseDTO
import com.josefilipe.digitalmenu.backend.model.User
import com.josefilipe.digitalmenu.backend.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
){
        //function to create user
    fun create(userRequest: UserRequestDTO): UserResponseDTO {
        val user = User(
            name = userRequest.name,
            email = userRequest.email,
            password = passwordEncoder.encode(userRequest.password),
            type = userRequest.type
        )
        val savedUser = userRepository.save(user)

        return UserResponseDTO(
            id = savedUser.id,
            name = savedUser.name,
            email = savedUser.email,
            type = savedUser.type
        )
    }

}