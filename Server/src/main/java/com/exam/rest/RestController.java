package com.exam.rest;

import com.exam.domain.User;
import com.exam.domain.UserType;
import com.exam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/user")
public class RestController {
    private final UserRepository userRepo;

    @Autowired
    public RestController(UserRepository userRepository) {
        this.userRepo = userRepository;
    }

    @GetMapping()
    public Iterable<User> getUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/{userType}")
    public Iterable<User> getUsersByType(@PathVariable UserType userType) {
        return userRepo.findAllByUserType(userType);
    }

    @PostMapping()
    public String saveUser(@RequestBody User user) {
        try {
            if (userRepo.save(user) == null)
                return "User saved";
            else
                return "User not saved";
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage(), ex);
        }
    }

    @PutMapping("/{id}")
    public String updateUser(@RequestBody User newUser, @PathVariable Integer id) {
        newUser.setId(id);
        try {
            User oldUser = userRepo.findByID(id);
            if (oldUser == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist");
            userRepo.update(newUser);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage(), ex);
        }
        return "User updated";
    }

    @DeleteMapping("/{id}")
    public String userDelete(@PathVariable Integer id) {
        try {
            userRepo.delete(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
        return "User deleted";
    }
}
