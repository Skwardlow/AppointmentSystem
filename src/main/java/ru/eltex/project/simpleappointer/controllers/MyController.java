package ru.eltex.project.simpleappointer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Controller class
 * @author skwardlow
 * @see Controller
 * @version 1.0
 * */
@Controller
public class MyController {
    /**
     * Mapping for index page
     * @return Index page as String to user
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexShow(){
        return "index";
    }
    /**
     * Mapping for registration page
     * @return registration page as String to user
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "registration";
    }
    /**
     * Mapping for admin page
     * @return admin page as String to user
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPanelShow(){
        return "admin";
    }
    /**
     * Mapping for client page
     * @return client page as String to user
     */
    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public String clientPanelShow(){return "client";}
    /**
     * Mapping for specialist page
     * @return specialist page as String to user
     */
    @RequestMapping(value = "/specialist", method = RequestMethod.GET)
    public String specialistPanelShow(){return "specialist";}
}
