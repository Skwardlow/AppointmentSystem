package ru.eltex.project.simpleappointer.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRESTController {

    @RequestMapping(value = "/")
    @ResponseBody
    public String welcome(){
        return "Hehey";
    }
}
