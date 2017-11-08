package com.liveasy.demo.controller;

import com.liveasy.demo.model.User;
import com.liveasy.demo.model.UserSubmodels.Preference;
import com.liveasy.demo.model.segregatedModel.Answer;
import com.liveasy.demo.service.UserService;
import com.liveasy.demo.service.preferenceService.PreferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@Slf4j
public class QuestionController {


    private UserService userService;
    private PreferenceService preferenceService;

    public QuestionController(UserService userService, PreferenceService preferenceService) {
        this.userService = userService;
        this.preferenceService = preferenceService;
    }


    @GetMapping(value="/question")
    public String questionPage(Model model, Principal principal){
        return "question/index";
    }

    @PostMapping(value="/question")
    public String questionProcess(@ModelAttribute("answer") Answer answer){

        log.debug(answer.getOne().toString());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        User user = userService.findByUsername(name);
        Preference preference = user.getPreference();

        preference.getPreference().add(answer.getOne());
        preference.getPreference().add(answer.getTwo());
        preference.getPreference().add(answer.getThree());
        preference.getPreference().add(answer.getFour());
        preference.getPreference().add(answer.getFive());
        preference.getPreference().add(answer.getSix());
        preference.getPreference().add(answer.getSeven());

        preference.setUser(user);
        preferenceService.saveOrUpdate(preference);

        return "index";
    }


}
