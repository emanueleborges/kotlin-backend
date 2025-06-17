// User.kt
package com.example

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "app_user") // Avoiding reserved keyword
@Schema(description = "User entity representing a system user")
data class User(
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the user", example = "1")
    val id: Long = 0,
    
    @Column(nullable = false)
    @Schema(description = "Name of the user", example = "John Doe", required = true)
    val name: String,
    
    @Column(name = "created_at", nullable = false)
    @Schema(description = "Timestamp when the user was created", example = "2023-01-01T12:00:00")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    
    @Column(name = "updated_at")
    @Schema(description = "Timestamp when the user was last updated", example = "2023-01-02T12:00:00")
    var updatedAt: LocalDateTime? = null
) {
    // Pre-update lifecycle hook to automatically update the updatedAt timestamp
    @PreUpdate
    fun onUpdate() {
        updatedAt = LocalDateTime.now()
    }
}