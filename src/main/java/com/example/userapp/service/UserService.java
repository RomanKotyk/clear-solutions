package com.example.userapp.service;

import com.example.userapp.exception.AgeValidationException;
import com.example.userapp.exception.InvalidDateRangeException;
import com.example.userapp.exception.UserNotFoundException;
import com.example.userapp.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService{
    User create(User user) throws AgeValidationException;
    void delete(Long id);
    User getById(Long id) throws UserNotFoundException;
    List<User> getAll(LocalDate from, LocalDate to) throws InvalidDateRangeException;
    User update(User user) throws AgeValidationException;
}
