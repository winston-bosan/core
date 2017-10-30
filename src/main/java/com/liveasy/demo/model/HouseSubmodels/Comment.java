package com.liveasy.demo.model.HouseSubmodels;


import com.liveasy.demo.model.House;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private House house;
    private ArrayList<String> comments;

    public Comment(ArrayList<String> comments) {
        this.comments = comments;
    }
}
