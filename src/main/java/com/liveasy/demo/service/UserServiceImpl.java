package com.liveasy.demo.service;

import com.liveasy.demo.command.UserCommand;
import com.liveasy.demo.converter.UserConverter.UserCommandToUser;
import com.liveasy.demo.converter.UserConverter.UserToUserCommand;
import com.liveasy.demo.exception.NotFoundException;
import com.liveasy.demo.model.User;
import com.liveasy.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    public Set<User> getAllUsers() {
        Set<User> test = new HashSet<>( (Collection) userRepository.findAll());
        //return new ArrayList<>(test);
        return test;
    }

    @Override
    public User findById(Long l) {

        Optional<User> weWant = userRepository.findById(l);

        if(!weWant.isPresent()){
            throw new NotFoundException("User not found! For User ID of : " + l.toString());
        }

        return weWant.get();
    }

    @Transactional
    @Override
    public UserCommand saveUserCommand(UserCommand command) {
        User tempUser = userCommandToUser.convert(command);
        User savedUser = userRepository.save(tempUser);
        log.debug("Saved User ID: " + tempUser.getId());
        return userToUserCommand.convert(savedUser);
    }

    @Override
    public UserCommand findCommandById(Long l) {
        return userToUserCommand.convert(findById(l));
    }

    @Override
    public void deleteById(Long l){
        userRepository.deleteById(l);
    }
}
