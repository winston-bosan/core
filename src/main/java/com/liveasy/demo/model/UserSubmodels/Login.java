package com.liveasy.demo.model.UserSubmodels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Login {

    private String username;
    private String password;
    private String md5;
    private String sha1;
    private String shad256;

}