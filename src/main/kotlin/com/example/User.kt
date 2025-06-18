// User.kt
package com.example

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "app_user") // Avoiding reserved keyword
data class User(
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(nullable = false)
    val name: String,
    
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null
) {
    // Pre-update lifecycle hook to automatically update the updatedAt timestamp
    @PreUpdate
    fun onUpdate() {
        updatedAt = LocalDateTime.now()
    }
}