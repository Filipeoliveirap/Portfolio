package com.josefilipe.digitalmenu.backend.service

import com.josefilipe.digitalmenu.backend.model.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService {

    private val secret = "supersecretdigitalmenu1234567890123456"
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateToken(user: User): String {
        return Jwts.builder()
            .setSubject(user.email)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4)) // 4 horas
            .signWith(secretKey)
            .compact()
    }

    fun extractEmail(token: String): String? {
        return try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .body

            claims.subject
        } catch (e: Exception) {
            null
        }
    }

    fun isTokenValid(token: String, email: String): Boolean {
        val extractedEmail = extractEmail(token)
        return extractedEmail == email && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
            .expiration
        return expiration.before(Date())
    }
}
