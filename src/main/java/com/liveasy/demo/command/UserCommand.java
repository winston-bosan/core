package com.liveasy.demo.command;

import com.liveasy.demo.model.House;
import com.liveasy.demo.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserCommand {


    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String description;
    private int active;
    private Set<House> houses = new HashSet<>();
    private Role role;

}
