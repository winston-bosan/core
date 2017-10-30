package com.liveasy.demo.converter.CommentConverter;

import com.liveasy.demo.command.HouseSubcommands.CommentCommand;
import com.liveasy.demo.model.HouseSubmodels.Comment;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentCommand implements Converter<Comment,CommentCommand> {

    @Synchronized
    @Nullable
    @Override
    public CommentCommand convert(Comment source) {
        //todo think of a good a way to do checknull logic
        //if(source.getId() == null){return null;}

        final CommentCommand commentCommand = new CommentCommand();

        commentCommand.setHouse(source.getHouse());
        commentCommand.setComments(source.getComments());
        commentCommand.setId(source.getId());

        return commentCommand;

    }

}
