package com.example

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.mockito.Mockito.*
import java.time.LocalDateTime
import java.util.Optional

@WebMvcTest(UserController::class)
class UserControllerTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    @Test
    fun `test get all users`() {
        val user1 = User(1, "User 1", LocalDateTime.now(), null)
        val user2 = User(2, "User 2", LocalDateTime.now(), null)
        
        `when`(userService.getAllUsers()).thenReturn(listOf(user1, user2))

        mockMvc.perform(get("/api/v1/users"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("User 1"))
            .andExpect(jsonPath("$[1].id").value(2))
            .andExpect(jsonPath("$[1].name").value("User 2"))
    }

    @Test
    fun `test get user by id when exists`() {
        val user = User(1, "User 1", LocalDateTime.now(), null)
        
        `when`(userService.getUserById(1)).thenReturn(Optional.of(user))

        mockMvc.perform(get("/api/v1/users/1"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("User 1"))
    }

    @Test
    fun `test get user by id when not exists`() {
        `when`(userService.getUserById(99)).thenReturn(Optional.empty())

        mockMvc.perform(get("/api/v1/users/99"))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `test create user`() {
        val newUser = User(0, "New User", LocalDateTime.now(), null)
        val savedUser = User(1, "New User", LocalDateTime.now(), null)
        
        `when`(userService.createUser(any())).thenReturn(savedUser)

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New User\"}"))
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("New User"))
    }

    @Test
    fun `test update user when exists`() {
        val updatedUser = User(1, "Updated User", LocalDateTime.now(), LocalDateTime.now())
        
        `when`(userService.updateUser(eq(1), any())).thenReturn(Optional.of(updatedUser))

        mockMvc.perform(put("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated User\"}"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Updated User"))
    }

    @Test
    fun `test update user when not exists`() {
        `when`(userService.updateUser(eq(99), any())).thenReturn(Optional.empty())

        mockMvc.perform(put("/api/v1/users/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated User\"}"))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `test delete user when exists`() {
        `when`(userService.deleteUser(1)).thenReturn(true)

        mockMvc.perform(delete("/api/v1/users/1"))
            .andExpect(status().isNoContent)
    }

    @Test
    fun `test delete user when not exists`() {
        `when`(userService.deleteUser(99)).thenReturn(false)

        mockMvc.perform(delete("/api/v1/users/99"))
            .andExpect(status().isNotFound)
    }
}
