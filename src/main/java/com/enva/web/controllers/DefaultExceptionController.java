package com.enva.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionController {

    private static Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionController.class);

    @ExceptionHandler(Exception.class)
    public String showError(Exception ex) {

        LOGGER.error("Exception:",ex);
        return "error";
    }

}
