package com.example.userapp;

import com.example.userapp.dto.UserDTO;
import com.example.userapp.exception.UserNotFoundException;
import com.example.userapp.model.User;
import com.example.userapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserAppApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testCreateUser() throws Exception {
        UserDTO userDTO = new UserDTO("romankotyk2004@gmail.com", "Roman", "Kotyk", LocalDate.of(2004,2,17),"Kyiv","12345867"); // Create a UserDTO object with test data
        User createdUser = new User(1L, "romankotyk2004@gmail.com", "Roman", "Kotyk", LocalDate.of(2004,2,17),"Kyiv","12345867");
        Mockito.when(userService.create(Mockito.any(User.class))).thenReturn(createdUser);

        mockMvc.perform(
                        post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.convertObjectToJsonBytes(userDTO)))
                        .andExpect(MockMvcResultMatchers.status().isCreated())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(userDTO.getEmail()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(userDTO.getFirstName()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(userDTO.getLastName()));
    }

    @Test
    public void testDeleteUser() throws Exception {
        Long userId = 1L;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/{id}", userId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User(1L, "romankotyk2004@gmail.com", "Roman", "Kotyk", LocalDate.of(2004,2,17),"Kyiv","12345867");
        Mockito.when(userService.getById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(user.getEmail()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(user.getFirstName()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(user.getLastName()));
    }


}
