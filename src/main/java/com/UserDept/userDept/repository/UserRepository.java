package com.UserDept.userDept.repository;

import com.UserDept.userDept.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User>findByActiveTrue();
}
