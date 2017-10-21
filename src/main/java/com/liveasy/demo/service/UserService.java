package com.liveasy.demo.service;

import com.liveasy.demo.command.UserCommand;
import com.liveasy.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User findById(Long l);
    UserCommand saveUserCommand(UserCommand command);

}
