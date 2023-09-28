package com.example.userapp.controller;

import com.example.userapp.dto.UserDTO;
import com.example.userapp.exception.AgeValidationException;
import com.example.userapp.exception.InvalidDateRangeException;
import com.example.userapp.exception.UserNotFoundException;
import com.example.userapp.model.User;
import com.example.userapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid UserDTO userDTO) throws AgeValidationException{
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthDate(userDTO.getBirthDate());
        user.setAddress(userDTO.getAddress());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.create(user));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.userService.delete(id);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) throws UserNotFoundException {
        return this.userService.getById(id);
    }

    @GetMapping
    public List<User> getAll(@RequestParam LocalDate from, @RequestParam LocalDate to) throws InvalidDateRangeException {
        return this.userService.getAll(from,to);
    }

    @PutMapping
    public User update(@RequestBody User user) throws AgeValidationException {
        return this.userService.update(user);
    }
}
