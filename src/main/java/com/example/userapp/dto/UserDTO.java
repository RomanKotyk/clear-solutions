package com.example.userapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class UserDTO {
    @NotBlank(message = "Could not be empty")
    @Email(message = "Please write in the right format: example@gmail.com")
    private String email;
    @NotBlank(message = "Could not be empty")
    private String firstName;
    @NotBlank(message = "Could not be empty")
    private String lastName;
    @Past(message = "Must be earlier than current date\n")
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;


    public UserDTO() {
    }

    public UserDTO(String email, String firstName, String lastName, LocalDate birthDate, String address, String phoneNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
