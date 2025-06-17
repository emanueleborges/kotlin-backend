
// User.kt
package com.example

import jakarta.persistence.*

@Entity
@Table(name = "app_user") // <-- Evita palavra reservada
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String
)