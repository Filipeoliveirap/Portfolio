package com.josefilipe.digitalmenu.backend.model


import jakarta.persistence.*

@Entity
@Table(name = "product")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 100)
    val name: String,

    @Column(columnDefinition = "TEXT")
    val description: String? = null,

    @Column(nullable = false)
    val price: Double,

    @Column(name = "image_url")
    val imageUrl: String? = null,

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    val category: Category

)
