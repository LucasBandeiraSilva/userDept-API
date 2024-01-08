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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<Object>newUser(@RequestBody @Valid UserRecordDto userRecordDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
        var newUser = new User();
        BeanUtils.copyProperties(userRecordDto,newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(newUser));
    }
}
