package com.josefilipe.digitalmenu.backend.service

import com.josefilipe.digitalmenu.backend.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
@Service
class UserDetailsServiceImpl (
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)?: throw UsernameNotFoundException("User $email" +
                " not found")
        return org.springframework.security.core.userdetails.User.withUsername(user.email).password(user.password).authorities(user.type.name).build()
    }
}