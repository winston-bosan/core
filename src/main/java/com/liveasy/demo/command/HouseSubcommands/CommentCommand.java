package com.liveasy.demo.command.HouseSubcommands;

import com.liveasy.demo.model.House;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
@NoArgsConstructor
public class CommentCommand {
    private Long id;
    private House house;
    private ArrayList<String> comments;

    @Override
    public String toString() {
        return "CommentCommand{" +
                "id=" + id +
                ", house=" + house +
                ", comments=" + comments +
                '}';
    }
}
