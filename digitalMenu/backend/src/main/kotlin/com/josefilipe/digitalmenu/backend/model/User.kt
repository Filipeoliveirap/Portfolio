package com.josefilipe.digitalmenu.backend.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 100)
    val name: String,

    @Column(nullable = false, unique = true, length = 100)
    val email: String,

    @Column(nullable = false, length = 225)
    val password: String,

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    val type: UserType
)
