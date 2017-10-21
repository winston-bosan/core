package com.liveasy.demo.service;

import com.liveasy.demo.command.UserCommand;
import com.liveasy.demo.converter.UserCommandToUser;
import com.liveasy.demo.converter.UserToUserCommand;
import com.liveasy.demo.model.User;
import com.liveasy.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private UserCommandToUser userCommandToUser;
    private UserToUserCommand userToUserCommand;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserCommandToUser userCommandToUser, UserToUserCommand userToUserCommand) {
        this.userRepository = userRepository;
        this.userCommandToUser = userCommandToUser;
        this.userToUserCommand = userToUserCommand;
    }

    @Override
    public List<User> getAllUsers() {
        Set<User> test = new HashSet<>( (Collection) userRepository.findAll());
        return new ArrayList<>(test);
    }

    @Override
    public User findById(Long l) {
        User weWant = userRepository.findById(l).get();

        if(weWant==null){
            throw new RuntimeException("User Not Found!");
        }

        return weWant;
    }

    @Transactional
    @Override
    public UserCommand saveUserCommand(UserCommand command) {
        User tempUser = userCommandToUser.convert(command);
        User savedUser = userRepository.save(tempUser);
        log.debug("Saved Recipe ID: " + tempUser.getId());
        return userToUserCommand.convert(savedUser);
    }

}
