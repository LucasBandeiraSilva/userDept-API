package com.UserDept.userDept.services;

import com.UserDept.userDept.controller.UserController;
import com.UserDept.userDept.entities.User;
import com.UserDept.userDept.repository.UserRepository;
import com.UserDept.userDept.userRecordDto.UserRecordDto;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Object> newUser(UserRecordDto userRecordDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
        var newUser = new User();
        BeanUtils.copyProperties(userRecordDto, newUser);
        newUser.setActive(true);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(newUser));
    }

    public ResponseEntity<List<User>> findAllUsers() {
        List<User> userList = userRepository.findByActiveTrue();
        if (userList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        for (User users : userList) {
            Long id = users.getId();
            users.add(linkTo(methodOn(UserController.class).findUserById(id)).withSelfRel());
        }
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    public ResponseEntity<User> findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            user.add(linkTo(methodOn(UserController.class).findAllUsers()).withRel("Users List"));
            return ResponseEntity.status(HttpStatus.FOUND).body(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity updateUser( UserRecordDto userRecordDto,  Long id, BindingResult bindingResult) {
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

    public ResponseEntity deleteUser( Long id) {
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
