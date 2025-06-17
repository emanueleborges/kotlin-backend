// UserController.kt
package com.example

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/users")
class UserController(private val userService: UserService) {
    
    @GetMapping
    fun getAll(): ResponseEntity<List<User>> = 
        ResponseEntity.ok(userService.getAllUsers())
    
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<User> {
        val user = userService.getUserById(id)
        return if (user.isPresent) {
            ResponseEntity.ok(user.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody user: User): ResponseEntity<User> {
        val createdUser = userService.createUser(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser)
    }
    
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody user: User): ResponseEntity<User> {
        val updatedUser = userService.updateUser(id, user)
        return if (updatedUser.isPresent) {
            ResponseEntity.ok(updatedUser.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        return if (userService.deleteUser(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @GetMapping("/search")
    fun findByName(@RequestParam name: String): ResponseEntity<List<User>> =
        ResponseEntity.ok(userService.getUsersByName(name))
        
    @GetMapping("/recent")
    fun findRecentUsers(@RequestParam days: Long = 30): ResponseEntity<List<User>> {
        val since = LocalDateTime.now().minusDays(days)
        return ResponseEntity.ok(userService.getRecentUsers(since))
    }
}
