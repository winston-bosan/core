package com.liveasy.demo.model.UserSubmodels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Personal {

    private String phone;
    private String company;
    private String jobTitle;
    private Set<String> language;



}
