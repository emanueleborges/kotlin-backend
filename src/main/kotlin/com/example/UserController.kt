// UserController.kt
package com.example

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Management", description = "API endpoints for user management")
class UserController(private val userService: UserService) {
    
    @Operation(summary = "Get all users", description = "Retrieves a list of all users in the system")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successfully retrieved the list of users")
    ])
    @GetMapping
    fun getAll(): ResponseEntity<List<User>> = 
        ResponseEntity.ok(userService.getAllUsers())
    
    @Operation(summary = "Get user by ID", description = "Retrieves a specific user by their unique identifier")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successfully retrieved the user"),
        ApiResponse(responseCode = "404", description = "User not found")
    ])
    @GetMapping("/{id}")
    fun getById(
        @Parameter(description = "The unique identifier of the user") 
        @PathVariable id: Long
    ): ResponseEntity<User> {
        val user = userService.getUserById(id)
        return if (user.isPresent) {
            ResponseEntity.ok(user.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @Operation(summary = "Create a new user", description = "Creates a new user in the system")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "User successfully created")
    ])
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @Parameter(description = "User details for creating a new user") 
        @RequestBody user: User
    ): ResponseEntity<User> {
        val createdUser = userService.createUser(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser)
    }
    
    @Operation(summary = "Update an existing user", description = "Updates an existing user's information")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "User successfully updated"),
        ApiResponse(responseCode = "404", description = "User not found")
    ])
    @PutMapping("/{id}")
    fun update(
        @Parameter(description = "The unique identifier of the user to update") 
        @PathVariable id: Long,
        
        @Parameter(description = "Updated user details") 
        @RequestBody user: User
    ): ResponseEntity<User> {
        val updatedUser = userService.updateUser(id, user)
        return if (updatedUser.isPresent) {
            ResponseEntity.ok(updatedUser.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @Operation(summary = "Delete a user", description = "Deletes a user from the system")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "User successfully deleted"),
        ApiResponse(responseCode = "404", description = "User not found")
    ])
    @DeleteMapping("/{id}")
    fun delete(
        @Parameter(description = "The unique identifier of the user to delete") 
        @PathVariable id: Long
    ): ResponseEntity<Void> {
        return if (userService.deleteUser(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @Operation(summary = "Search users by name", description = "Finds users whose names match the provided search term")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successfully retrieved matching users")
    ])
    @GetMapping("/search")
    fun findByName(
        @Parameter(description = "Name to search for") 
        @RequestParam name: String
    ): ResponseEntity<List<User>> =
        ResponseEntity.ok(userService.getUsersByName(name))
        
    @Operation(summary = "Get recently added users", description = "Retrieves users added within the specified number of days")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successfully retrieved recent users")
    ])
    @GetMapping("/recent")
    fun findRecentUsers(
        @Parameter(description = "Number of days to look back (default: 30)") 
        @RequestParam days: Long = 30
    ): ResponseEntity<List<User>> {
        val since = LocalDateTime.now().minusDays(days)
        return ResponseEntity.ok(userService.getRecentUsers(since))
    }
}
