package com.liveasy.demo.converter.CommentConverter;

import com.liveasy.demo.command.HouseSubcommands.CommentCommand;
import com.liveasy.demo.model.HouseSubmodels.Comment;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommentCommandToComment implements Converter<CommentCommand,Comment> {

    @Synchronized
    @Nullable
    @Override
    public Comment convert(CommentCommand source) {
        //todo think of a good a way to do checknull logic
        //if(source.getId() == null){return null;}

        final Comment comment = new Comment();

        comment.setHouse(source.getHouse());
        comment.setComments(source.getComments());
        comment.setId(source.getId());

        return comment;

    }

}
