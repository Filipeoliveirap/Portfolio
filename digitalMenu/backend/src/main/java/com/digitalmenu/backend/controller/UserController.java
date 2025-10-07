package com.digitalmenu.backend.controller;

import com.digitalmenu.backend.business.DTOs.usersDTOS.LoginRequestDTO;
import com.digitalmenu.backend.business.DTOs.usersDTOS.UserRequestDTO;
import com.digitalmenu.backend.business.DTOs.usersDTOS.UserResponseDTO;
import com.digitalmenu.backend.business.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //cadastra um novo usuario
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponse = userService.register(userRequestDTO);
        return ResponseEntity.ok(userResponse);
    }

    //autentica um usuario
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        UserResponseDTO userResponse = userService.login(loginRequestDTO);
        return ResponseEntity.ok(userResponse);
    }
}
