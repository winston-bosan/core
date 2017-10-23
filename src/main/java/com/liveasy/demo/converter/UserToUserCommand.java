package com.liveasy.demo.converter;

import com.liveasy.demo.command.UserCommand;
import com.liveasy.demo.model.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserToUserCommand implements Converter<User,UserCommand> {


    @Nullable
    @Override
    @Synchronized
    public UserCommand convert(User source) {
        //todo think of a good a way to do checknull logic
        //if(source.getId() == null){return null;}

        UserCommand userCommand = new UserCommand();

        userCommand.setId(source.getId());
        userCommand.setActive(source.getActive());
        userCommand.setEmail(source.getEmail());
        userCommand.setFirstName(source.getFirstName());
        userCommand.setLastName(source.getLastName());
        userCommand.setHouses(source.getHouses());
        userCommand.setPassword(source.getPassword());
        userCommand.setRole(source.getRole());
        userCommand.setDescription(source.getDescription());
        userCommand.setPurpose(source.getPurpose());


        return userCommand;

    }

    @Nullable
    @Synchronized
    public UserCommand convertNoId(User source) {
        //todo think of a good a way to do checknull logic
        //if(source.getId() == null){return null;}

        UserCommand userCommand = new UserCommand();

        userCommand.setActive(source.getActive());
        userCommand.setEmail(source.getEmail());
        userCommand.setFirstName(source.getFirstName());
        userCommand.setLastName(source.getLastName());
        userCommand.setHouses(source.getHouses());
        userCommand.setPassword(source.getPassword());
        userCommand.setRole(source.getRole());
        userCommand.setDescription(source.getDescription());
        userCommand.setPurpose(source.getPurpose());


        return userCommand;

    }

}