package com.digitalmenu.backend.business.mapper;

import com.digitalmenu.backend.business.DTOs.usersDTOS.UserRequestDTO;
import com.digitalmenu.backend.business.DTOs.usersDTOS.UserResponseDTO;
import com.digitalmenu.backend.business.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequestDTO dto) {
        if (dto == null) return null;

        return User.builder().name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    public UserResponseDTO toResponseDTO(User entity) {
        if (entity == null) return null;

        return UserResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .createAt(entity.getCreatedAt())
                .build();
    }
}
