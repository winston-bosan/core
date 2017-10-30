package com.liveasy.demo.command;

import com.liveasy.demo.model.HouseSubmodels.Comment;
import com.liveasy.demo.model.HouseSubmodels.Layout;
import com.liveasy.demo.model.HouseSubmodels.Location;
import com.liveasy.demo.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
public class HouseCommand {

    private Long id;
    private User user;

    private Location location;
    private Layout layout;
    private Comment comment;

    private Byte[] image;

    @Override
    public String toString() {
        return "HouseCommand{" +
                "id=" + id +
                ", user=" + user +
                ", location=" + location +
                ", layout=" + layout +
                ", comment=" + comment +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
