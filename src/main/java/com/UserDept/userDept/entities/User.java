package com.UserDept.userDept.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
public class User extends RepresentationModel<User> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String email;
    @JsonIgnore
    private String password;
    private Boolean active;

    @ManyToOne
    private Department department;


}
