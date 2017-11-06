package com.liveasy.demo.service;

import com.liveasy.demo.command.UserCommand;
import com.liveasy.demo.converter.UserConverter.UserCommandToUser;
import com.liveasy.demo.converter.UserConverter.UserToUserCommand;
import com.liveasy.demo.exception.NotFoundException;
import com.liveasy.demo.model.User;
import com.liveasy.demo.repository.UserRepository;
import com.liveasy.demo.service.encryptionService.EncryptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private UserCommandToUser userCommandToUser;
    private UserToUserCommand userToUserCommand;
    private EncryptionService encryptionService;

    public UserServiceImpl(UserRepository userRepository, UserCommandToUser userCommandToUser, UserToUserCommand userToUserCommand, EncryptionService encryptionService) {
        this.userRepository = userRepository;
        this.userCommandToUser = userCommandToUser;
        this.userToUserCommand = userToUserCommand;
        this.encryptionService = encryptionService;
    }

    /*
         *
         */
    @Override
    public boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    @Override
    public User registerNewUser(UserCommand userCommand) {
        return null;
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

    @Override
    public User findByUsername(String username) {
        return findByEmail(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); //fun with Java 8
        return users;
    }

    @Override
    public User getById(Long id) {
        Optional<User> weWant = userRepository.findById(id);

        if(!weWant.isPresent()){
            throw new NotFoundException("User not found! For User ID of : " + id.toString());
        }

        return weWant.get();
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        if(domainObject.getPassword() != null){
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }
        return userRepository.save(domainObject);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
