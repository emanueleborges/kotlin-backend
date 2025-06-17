// UserRepository.kt
package com.example

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

/**
 * Repository interface for User entity
 * 
 * This interface provides methods to interact with the User data in the database.
 * It extends JpaRepository to inherit basic CRUD operations and adds custom queries.
 */
@Repository
interface UserRepository : JpaRepository<User, Long> {
    
    /**
     * Find users by exact name match
     * 
     * @param name The name to search for
     * @return List of users with the exact matching name
     */
    fun findByName(name: String): List<User>
    
    /**
     * Find users with names containing the specified string (case insensitive)
     * 
     * @param name The name pattern to search for
     * @return List of users with names containing the pattern
     */
    fun findByNameContainingIgnoreCase(name: String): List<User>
    
    /**
     * Find users created after the specified date
     * 
     * @param date The date to compare against
     * @return List of users created after the specified date
     */
    @Query("SELECT u FROM User u WHERE u.createdAt > :date")
    fun findRecentUsers(@Param("date") date: LocalDateTime): List<User>
    
    /**
     * Find users with pagination support
     * 
     * @param name The name pattern to search for
     * @param pageable Pagination information
     * @return Page of users with names containing the pattern
     */
    fun findByNameContainingIgnoreCase(name: String, pageable: Pageable): Page<User>
    
    /**
     * Check if a user with the specified name exists
     * 
     * @param name The name to check
     * @return true if a user with the name exists, false otherwise
     */
    fun existsByName(name: String): Boolean
    
    /**
     * Count users created after the specified date
     * 
     * @param date The date to compare against
     * @return Number of users created after the specified date
     */
    @Query("SELECT COUNT(u) FROM User u WHERE u.createdAt > :date")
    fun countRecentUsers(@Param("date") date: LocalDateTime): Long
}
