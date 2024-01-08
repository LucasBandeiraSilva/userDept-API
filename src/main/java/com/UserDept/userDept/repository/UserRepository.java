package com.UserDept.userDept.repository;

import com.UserDept.userDept.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
