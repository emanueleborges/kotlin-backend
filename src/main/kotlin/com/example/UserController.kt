
// UserController.kt
package com.example

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(val repository: UserRepository) {
    @GetMapping fun getAll() = repository.findAll()
    @PostMapping fun create(@RequestBody user: User) = repository.save(user)
    @PutMapping("/{id}") fun update(@PathVariable id: Long, @RequestBody user: User) = repository.save(user.copy(id = id))
    @DeleteMapping("/{id}") fun delete(@PathVariable id: Long) = repository.deleteById(id)
}
