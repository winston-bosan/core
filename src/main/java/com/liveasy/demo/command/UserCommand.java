package com.liveasy.demo.command;

import com.liveasy.demo.model.House;
import com.liveasy.demo.model.Purpose;
import com.liveasy.demo.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserCommand {


    private Long id;

    @Email
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotBlank(message = "{firstName.notBlank}")
    private String firstName;

    @NotBlank(message = "{lastName.notBlank}")
    private String lastName;

    private String description;
    private int active;
    private Set<House> houses = new HashSet<>();
    private List<Role> roles = new ArrayList<>();
    private Purpose purpose;



}
