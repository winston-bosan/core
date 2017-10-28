package com.liveasy.demo.controllerAdvice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class) //Note, this is a root-cause class, not a local exception
    public ModelAndView handleNumberFormat(Exception exception ){
        log.error("***** Handling Number Format *****  ");
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/400error");
        modelAndView.addObject("exception",exception);


        return modelAndView;
    }

}
