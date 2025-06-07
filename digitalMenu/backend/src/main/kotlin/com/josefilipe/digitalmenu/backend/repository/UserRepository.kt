package com.josefilipe.digitalmenu.backend.repository

import com.josefilipe.digitalmenu.backend.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

}