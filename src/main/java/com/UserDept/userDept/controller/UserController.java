package com.UserDept.userDept.controller;

import com.UserDept.userDept.entities.User;
import com.UserDept.userDept.repository.UserRepository;
import com.UserDept.userDept.userRecordDto.UserRecordDto;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<Object> newUser(@RequestBody @Valid UserRecordDto userRecordDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
        var newUser = new User();
        BeanUtils.copyProperties(userRecordDto, newUser);
        newUser.setActive(true);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(newUser));
    }

    @GetMapping()
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        for (User users : userList) {
            Long id = users.getId();
            users.add(linkTo(methodOn(UserController.class).findUserById(id)).withSelfRel());
        }
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(optionalUser.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody @Valid UserRecordDto userRecordDto, @PathVariable Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            BeanUtils.copyProperties(userRecordDto, user);
            userRepository.save(user);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            var user = userOptional.get();
            user.setActive(false);
            userRepository.save(user);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
