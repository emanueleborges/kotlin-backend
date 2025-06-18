package com.example

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.Optional

@Service
class UserService(private val repository: UserRepository) {
    
    fun getAllUsers(): List<User> = repository.findAll()
      fun getUserById(id: Long): Optional<User> = repository.findById(id)
    
    /**
     * Search users by name pattern
     * Changed from exact match to case-insensitive partial match
     */
    fun getUsersByName(name: String): List<User> = repository.findByNameContainingIgnoreCase(name)
    
    @Transactional
    fun createUser(user: User): User {
        // Ensure we're creating a new user
        val newUser = user.copy(
            id = 0,
            createdAt = LocalDateTime.now(),
            updatedAt = null
        )
        return repository.save(newUser)
    }
      @Transactional
    fun updateUser(id: Long, user: User): Optional<User> {
        return if (repository.existsById(id)) {
            val existingUser = repository.findById(id).get()
            val updatedUser = user.copy(
                id = id,
                createdAt = existingUser.createdAt,
                updatedAt = LocalDateTime.now()
            )
            Optional.of(repository.save(updatedUser))
        } else {
            Optional.empty()
        }
    }
    
    @Transactional
    fun deleteUser(id: Long): Boolean {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            true
        } else {
            false
        }
    }
    
    fun getRecentUsers(since: LocalDateTime): List<User> = repository.findRecentUsers(since)
}
