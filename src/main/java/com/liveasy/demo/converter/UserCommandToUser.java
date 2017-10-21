package com.liveasy.demo.converter;

import com.liveasy.demo.command.UserCommand;
import com.liveasy.demo.model.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserCommandToUser implements Converter<UserCommand,User> {


    @Nullable
    @Override
    @Synchronized
    public User convert(UserCommand source) {
        if(source == null){return null;}

        User user = new User();

        user.setId(source.getId());
        user.setActive(source.getActive());
        user.setEmail(source.getEmail());
        user.setFirstName(source.getFirstName());
        user.setLastName(source.getLastName());
        user.setHouses(source.getHouses());
        user.setPassword(source.getPassword());
        user.setRole(source.getRole());
        user.setDescription(source.getDescription());


        return user;

    }
}