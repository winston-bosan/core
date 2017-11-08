package com.liveasy.demo.converter.UserConverter;

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
        userCommand.setRoles(source.getRoles());
        userCommand.setDescription(source.getDescription());
        userCommand.setPurpose(source.getPurpose());
        userCommand.setPreference(source.getPreference());
        userCommand.setProvider(source.getProvider());

        return userCommand;

    }


}