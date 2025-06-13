package com.josefilipe.digitalmenu.backend.controller

import com.josefilipe.digitalmenu.backend.dto.AuthenticationRequestDTO
import com.josefilipe.digitalmenu.backend.dto.AuthenticationResponseDTO
import com.josefilipe.digitalmenu.backend.service.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthenticationController (
    private val authenticationService: AuthenticationService
) {
    @PostMapping("/login")
    fun login(@RequestBody request: AuthenticationRequestDTO): ResponseEntity<AuthenticationResponseDTO> {
        val response = authenticationService.authenticate(request)
        return ResponseEntity.ok(response)
    }
}