package com.josefilipe.digitalmenu.backend.model

import jakarta.persistence.*

@Entity
@Table(name = "order_item")
data class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "quantity", nullable = false)
    val quantity: Int = 0,

    @Column(nullable = false)
    val unitPrice: Double = 0.0,

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    val order: CustomerOrder,

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product
)
