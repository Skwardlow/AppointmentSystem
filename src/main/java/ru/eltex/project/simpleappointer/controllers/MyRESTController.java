package ru.eltex.project.simpleappointer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.eltex.project.simpleappointer.entities.User;
import ru.eltex.project.simpleappointer.utils.RegUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController

public class MyRESTController {
    private static final Integer EXCLUDING_INDEX = 1;
    @Autowired
    RegUtil regUtil;

    @RequestMapping(value = "/reg_user",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Byte reg_user(@RequestBody String json) throws UnsupportedEncodingException {
        String[] req = URLDecoder.decode(json, "UTF-8").split("&");
        for(int i = 0; i < req.length; i++){
            int index = req[i].indexOf('=');
            req[i] = req[i].substring(index + EXCLUDING_INDEX);
            System.out.println(req[i]);
        }
        User user = new User(req[0] + " " + req[1] + " " + req[2], req[3] , req[3] , req[4] , req[5] );
        byte answer = regUtil.regAddU(user,req[5]);
        return answer;
    }
    @RequestMapping(value = "/login_user",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Byte login_user(@RequestBody String json) throws UnsupportedEncodingException {
        String[] req = URLDecoder.decode(json, "UTF-8").split("&");
        for(int i = 0; i < req.length; i++){
            int index = req[i].indexOf('=');
            req[i] = req[i].substring(index + EXCLUDING_INDEX);
            System.out.println(req[i]);
        }

        byte answer = 0;
        return answer;
    }
}
