package com.UserDept.userDept.controller;

import com.UserDept.userDept.entities.User;
import com.UserDept.userDept.services.UserService;
import com.UserDept.userDept.userRecordDto.UserRecordDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Object> newUser(@RequestBody @Valid UserRecordDto userRecordDto, BindingResult bindingResult) {
        return userService.newUser(userRecordDto,bindingResult);
    }

    @GetMapping()
    public ResponseEntity<List<User>> findAllUsers() {
        return  userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody @Valid UserRecordDto userRecordDto, @PathVariable Long id, BindingResult bindingResult) {
        return  userService.updateUser(userRecordDto,id,bindingResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
