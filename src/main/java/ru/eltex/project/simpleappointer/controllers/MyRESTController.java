package ru.eltex.project.simpleappointer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.eltex.project.simpleappointer.entities.User;
import ru.eltex.project.simpleappointer.utils.RegUtil;
import ru.eltex.project.simpleappointer.utils.SplitURL;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.util.ArrayList;

@RestController

public class MyRESTController {
    @Autowired
    RegUtil regUtil;
    @Autowired
    SplitURL splitURL;

    @RequestMapping(value = "/reg_user",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Byte reg_user(@RequestBody String object) throws UnsupportedEncodingException {
        ArrayList<String> req = splitURL.split(object);
        User user = new User(req.get(0) + " " + req.get(1) + " " + req.get(2), req.get(3) , req.get(3) , req.get(4) , req.get(5) );
        byte answer = regUtil.regAddU(user,req.get(5));
        return answer;
    }

    @RequestMapping(value = "/delete_spec",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer delete_spec(@RequestBody String object) throws UnsupportedEncodingException {
        ArrayList<String> req = splitURL.split(object);

        return 0;
    }

    @RequestMapping(value = "/delete_day",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer delete_day(@RequestBody String object) throws UnsupportedEncodingException {
        ArrayList<String> req = splitURL.split(object);

        return 0;
    }

    @RequestMapping(value = "/invite_reset",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer invite_reset(){

        return 0;
    }

    @RequestMapping(value = "/invite_create",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer invite_create(@RequestBody String object) throws UnsupportedEncodingException {
        ArrayList<String> req = splitURL.split(object);

        return 0;
    }

}
