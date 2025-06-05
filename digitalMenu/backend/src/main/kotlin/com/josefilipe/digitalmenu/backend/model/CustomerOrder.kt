package com.josefilipe.digitalmenu.backend.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "customer_order")
data class CustomerOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 100)
    val customerName: String,

    @Column(nullable = false, length = 20)
    val status: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "total", nullable = false)
    val total: Double = 0.0,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true )
    val items: List<OrderItem> = mutableListOf()
)
