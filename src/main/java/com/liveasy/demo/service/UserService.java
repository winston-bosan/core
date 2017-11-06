package com.liveasy.demo.service;

import com.liveasy.demo.command.UserCommand;
import com.liveasy.demo.model.User;
import com.liveasy.demo.service.segregatedInterface.CRUDService;

import java.util.Set;

public interface UserService extends CRUDService<User>{
    boolean emailExist(String email);
    User registerNewUser(UserCommand userCommand);

    //List<User> getAllUsers();
    Set<User> getAllUsers();

    User findById(Long l);
    UserCommand saveUserCommand(UserCommand command);
    UserCommand findCommandById(Long l);
    void deleteById(Long l);
    User findByUsername(String username);
    User findByEmail(String email);

}
