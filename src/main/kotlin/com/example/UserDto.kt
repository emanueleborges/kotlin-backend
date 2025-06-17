package com.example

import java.time.LocalDateTime

/**
 * Data Transfer Objects for User entity
 */
class UserDto {
    
    /**
     * DTO for creating a new user
     */
    data class CreateRequest(
        val name: String
    )
    
    /**
     * DTO for updating a user
     */
    data class UpdateRequest(
        val name: String
    )
    
    /**
     * DTO for user responses
     */
    data class Response(
        val id: Long,
        val name: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime?
    ) {
        companion object {
            fun fromEntity(user: User): Response {
                return Response(
                    id = user.id,
                    name = user.name,
                    createdAt = user.createdAt,
                    updatedAt = user.updatedAt
                )
            }
        }
    }
}
