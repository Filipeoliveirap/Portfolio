package com.josefilipe.digitalmenu.backend.controller

import com.josefilipe.digitalmenu.backend.dto.UserRequestDTO
import com.josefilipe.digitalmenu.backend.dto.UserResponseDTO
import com.josefilipe.digitalmenu.backend.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController (
    private val userService: UserService
){
    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequestDTO): ResponseEntity<UserResponseDTO> {
        val user = userService.createUser(userRequest)
        return ResponseEntity.ok(user)
    }
}



