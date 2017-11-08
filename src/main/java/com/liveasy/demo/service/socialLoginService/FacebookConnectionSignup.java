package com.liveasy.demo.service.socialLoginService;

import com.liveasy.demo.model.User;
import com.liveasy.demo.repository.RoleRepository;
import com.liveasy.demo.repository.UserRepository;
import com.liveasy.demo.service.UserService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp{

    private UserRepository userRepository;
    private UserService userService;
    private RoleRepository roleRepository;

    public FacebookConnectionSignup(UserRepository userRepository, UserService userService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    public String execute(Connection<?> connection){
        User user = new User();

        user.setUsername(connection.getDisplayName());
        user.setProvider("FACEBOOK");
        user.setPassword(randomAlphabetic(8));
        user.addRole(roleRepository.findByRole("FACEBOOK_USER"));
        userService.saveOrUpdate(user);

        return user.getUsername();
    }

}
