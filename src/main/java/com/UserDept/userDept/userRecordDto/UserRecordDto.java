package com.UserDept.userDept.userRecordDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record UserRecordDto(@NotBlank @Length(min = 3, max = 70) String name, @NotNull int age, @Email @NotBlank String email, @NotBlank @Length(min = 6) String password) {

}
