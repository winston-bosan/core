package com.liveasy.demo.service;

import com.liveasy.demo.command.UserCommand;
import com.liveasy.demo.model.User;

import java.util.Set;

public interface UserService {

    //List<User> getAllUsers();
    Set<User> getAllUsers();

    User findById(Long l);
    UserCommand saveUserCommand(UserCommand command);
    UserCommand findCommandById(Long l);

}
