package ru.eltex.project.simpleappointer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexShow(){
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "registration";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPanelShow(){
        return "admin";
    }
}
