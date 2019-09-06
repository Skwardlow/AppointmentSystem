package ru.eltex.project.simpleappointer.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eltex.project.simpleappointer.entities.User;
import ru.eltex.project.simpleappointer.services.DateService;
import ru.eltex.project.simpleappointer.services.UserService;
import ru.eltex.project.simpleappointer.utils.SplitURL;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * An REST-controller for mapping POST user requests to the server
 * @author Aaaaa988, skwardlow
 * @version 1.0
 * @see RestController
 */
@RestController
public class UserREST {
    /**
     * Wiring userservice for DAO interaction
     */
    @Autowired
    UserService userService;
    /**
     * Wiring split url util for splitting and getting incoming requests
     */
    @Autowired
    SplitURL splitURL;
    /**
     * Wiring date service for Date DAO interaction
     */
    @Autowired
    DateService dateService;

    /**
     * Registration method for creating a new user
     * @param object url not splitted body, comes to splitURL, contains fields for creating new user
     *               7 parameters - its spec. 6 - default user
     * @return answer of successful or not successful registration for explanation
     * @see UserService
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/reg_user",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Byte reg_user(@RequestBody String object) throws UnsupportedEncodingException {
        ArrayList<String> req = splitURL.split(object);
        if (req.size()==7){
            User user = new User(req.get(0) + " " + req.get(1) + " " + req.get(2), req.get(3) , req.get(3) , req.get(4) , req.get(6) );
            byte answer = userService.regAddU(user,req.get(5));
            return answer;
        }

        else {
            User user = new User(req.get(0) + " " + req.get(1) + " " + req.get(2), req.get(3) , req.get(3) , req.get(4) , req.get(5) );
            byte answer = userService.regAddU(user,null);
            return answer;
        }

    }

    /**
     * Getting Appointments in day method for client
     * @param object body of not splitted URL, contains username and date (ex '30/02/19')
     * @return json formed object with Date objects (date = appointment)
     * @throws UnsupportedEncodingException
     * @throws JsonProcessingException
     */
    @RequestMapping(value ="/get_dayclient",produces = MediaType.APPLICATION_JSON_VALUE)
    public String get_dayclient(@RequestBody String object) throws UnsupportedEncodingException, JsonProcessingException {
        ArrayList<String> req = splitURL.split(object);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return dateService.returnAppointmentsUser(req.get(1),req.get(0), name);
    }

    /**
     * Creating a new Appointment (date) method
     * @param object  URL request body, contains username of spec and user, index in day and date (ex '30/02/19')
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/write_on",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer write_on(@RequestBody String object) throws UnsupportedEncodingException {
        ArrayList<String> req = splitURL.split(object);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        dateService.writeData(req.get(1),Integer.valueOf(req.get(2)),req.get(0),name);
        return 0;
    }//0-спец 1-дата 2-хз -чекбоксы (индекс в дне)
    //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //        String name = authentication.getName();

}
