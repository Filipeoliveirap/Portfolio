package com.digitalmenu.backend.business.DTOs.usersDTOS;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createAt;
}
