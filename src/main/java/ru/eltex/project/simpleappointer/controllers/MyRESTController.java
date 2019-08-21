package ru.eltex.project.simpleappointer.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MyRESTController {

    @RequestMapping(value = "index/5",method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
    @ResponseBody
    public String welcome(){
        return "Hehey";
    }
}
