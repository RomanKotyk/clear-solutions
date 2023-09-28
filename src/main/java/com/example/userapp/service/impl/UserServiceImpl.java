package com.example.userapp.service.impl;

import com.example.userapp.exception.AgeValidationException;
import com.example.userapp.exception.InvalidDateRangeException;
import com.example.userapp.exception.UserNotFoundException;
import com.example.userapp.model.User;
import com.example.userapp.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {
    private List<User> USERS = Stream.of(
            new User(1L, "romankotyk2004@gmail.com", "Roman", "Kotyk", LocalDate.of(2004,2,17),"Kyiv","12345867"),
            new User(2L, "andriyyfa@gmail.com", "Andrii", "Farmako", LocalDate.of(2000,5,25),"Lviv","25845454"),
            new User(3L, "vladyslav2004@gmail.com", "Vladyslav", "Phomenko", LocalDate.of(1999,3,21),"Kyiv","134568764"),
            new User(4L, "romashka@gmail.com", "Roman", "Koshchuk", LocalDate.of(1998,6,5),"Kharkiv","456453210"),
            new User(5L, "nazarfinik@gmail.com", "Nazar", "Finik", LocalDate.of(1995,10,15),"Kyiv","69850814")
    ).collect(Collectors.toList());
    @Value("${user.min-age}")
    private Long minAge;
    @Override
    public User create(User user) throws AgeValidationException {
        if (!checkUserAge(user.getBirthDate())) {
            throw new AgeValidationException("User must be at least 18 years old.");
        }
        user.setId((long) (this.USERS.size()+1));
        this.USERS.add(user);
        return user;
    }

    @Override
    public void delete(Long id) {
        this.USERS.removeIf(user -> user.getId().equals(id));
    }

    @Override
    public User getById(Long id) throws UserNotFoundException {
        return this.USERS.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User with ID not found: " + id));
    }

    @Override
    public List<User> getAll(LocalDate from, LocalDate to) throws InvalidDateRangeException {
        if (from.isAfter(to)) {
            throw new InvalidDateRangeException("From date is earlier than to!");
        }
        return this.USERS.stream().filter(user -> user.getBirthDate().isAfter(from) && user.getBirthDate().isBefore(to)).collect(Collectors.toList());
    }

    @Override
    public User update(User user) throws AgeValidationException {
        User oldUser = this.USERS.get((int) (user.getId()-1));
        if (user.getEmail() == null) user.setEmail(oldUser.getEmail());
        if (user.getFirstName() == null) user.setFirstName(oldUser.getFirstName());
        if (user.getLastName() == null) user.setLastName(oldUser.getLastName());
        if (user.getBirthDate() == null) user.setBirthDate(oldUser.getBirthDate());
        if (user.getAddress() == null) user.setAddress(oldUser.getAddress());
        if (user.getPhoneNumber() == null) user.setPhoneNumber(oldUser.getPhoneNumber());
        if (!checkUserAge(user.getBirthDate())) {
            throw new AgeValidationException("User must be at least 18 years old.");
        }
        this.USERS.add((int) (user.getId()-1), user);
        return user;
    }

    private boolean checkUserAge(LocalDate userBirthDate){
        LocalDate currentDate = LocalDate.now();
        LocalDate eighteenYearsAgo = currentDate.minusYears(minAge);
        if (userBirthDate.isAfter(eighteenYearsAgo)) {
            return false;
        }
        return true;
    }
}
