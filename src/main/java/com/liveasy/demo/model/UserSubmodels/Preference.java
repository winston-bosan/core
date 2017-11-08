package com.liveasy.demo.model.UserSubmodels;

import com.liveasy.demo.model.User;
import com.liveasy.demo.model.segregatedModel.AbstractDomainClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.ArrayList;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Preference extends AbstractDomainClass {

    private ArrayList<Integer> preference = new ArrayList<>();
    @OneToOne
    private User user;
}
