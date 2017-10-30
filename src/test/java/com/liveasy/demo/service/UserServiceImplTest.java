package com.liveasy.demo.service;

import com.liveasy.demo.converter.UserConverter.UserCommandToUser;
import com.liveasy.demo.converter.UserConverter.UserToUserCommand;
import com.liveasy.demo.model.User;
import com.liveasy.demo.repository.HouseRepository;
import com.liveasy.demo.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    @Mock
    HouseRepository houseRepository;
    @Mock
    UserToUserCommand userToUserCommand;
    @Mock
    UserCommandToUser userCommandToUser;

    UserService userService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository, userCommandToUser, userToUserCommand);
    }

    @Test
    public void getAllUsersTest() throws Exception {
        //before
        //This line mimicks auto-id generation
        User user = new User(); user.setId(34L);
        HashSet<User> usersTemp = new HashSet();
        usersTemp.add(user);

        //when
        when(userService.getAllUsers()).thenReturn(usersTemp);

        //now
        Set<User> users = userService.getAllUsers();

        //after
        assertEquals(usersTemp.size(),1);
        verify(userRepository,times(1)).findAll();
        verify(userRepository, never()).findById(anyLong());


    }

    @Test
    public void findByIdTest() throws Exception {
    }

    @Test
    public void saveUserCommandTest() throws Exception {
    }

    @Test
    public void findCommandByIdTest() throws Exception {
    }

    @Test
    public void deleteByIdTest() throws Exception {
        //given
        Long idToDelete = Long.valueOf(43L);

        //when
        //No when, return type is nothing!

        userService.deleteById(idToDelete);

        //then
        verify(userRepository,times(1)).deleteById(anyLong());
    }


}