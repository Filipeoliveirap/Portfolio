package com.digitalmenu.backend.business.service;

import com.digitalmenu.backend.business.DTOs.usersDTOS.LoginRequestDTO;
import com.digitalmenu.backend.business.DTOs.usersDTOS.UserRequestDTO;
import com.digitalmenu.backend.business.DTOs.usersDTOS.UserResponseDTO;
import com.digitalmenu.backend.business.mapper.UserMapper;
import com.digitalmenu.backend.business.model.User;
import com.digitalmenu.backend.infrastructure.exception.custom.EmailAlreadyExistsException;
import com.digitalmenu.backend.infrastructure.exception.custom.InvalidCredentialsException;
import com.digitalmenu.backend.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserResponseDTO register(UserRequestDTO dto) {
        //verifica se ja existe um usuário com o email informado
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email ja cadastrado.");
        }
        //cria entidade a partir do dto
        User user = userMapper.toEntity(dto);

        //criptografa a senha
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //salva user
        User savedUser = userRepository.save(user);

        return userMapper.toResponseDTO(savedUser);
    }

    public UserResponseDTO login(LoginRequestDTO login) {
        //busca user pelo email
        User user = userRepository.findByEmail(login.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("E-mail ou senha inválidos."));
        //verigica se a senha é válida
        if(!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("E-mail ou senha inválidos.");
        }

        return userMapper.toResponseDTO(user);

    }
}
