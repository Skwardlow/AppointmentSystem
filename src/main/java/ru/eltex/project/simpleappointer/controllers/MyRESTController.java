package ru.eltex.project.simpleappointer.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.eltex.project.simpleappointer.entities.User;
import ru.eltex.project.simpleappointer.services.AdminService;
import ru.eltex.project.simpleappointer.services.DateService;
import ru.eltex.project.simpleappointer.services.UserService;
import ru.eltex.project.simpleappointer.utils.SplitURL;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * An rest controller class for mapping POST requests
 * @author Aaaaa988, Skwardlow
 * @see RestController
 */

@RestController
public class MyRESTController {

    @Autowired
    UserService userService;

    @Autowired
    SplitURL splitURL;

    @Autowired
    AdminService adminService;

    @Autowired
    DateService dateService;

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

    @RequestMapping(value = "/delete_spec",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer delete_spec(@RequestBody String object) throws UnsupportedEncodingException {
        ArrayList<String> req = splitURL.split(object);
            adminService.deleteByUsername(req.get(0));
        return 0;
    }

    @RequestMapping(value = "/delete_day",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer delete_day(@RequestBody String object) throws UnsupportedEncodingException {
        ArrayList<String> req = splitURL.split(object);
            dateService.dayWithAppointmentsDelete(req.get(0),req.get(1));
        return 0;
    }

    @RequestMapping(value = "/invite_reset",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer invite_reset(){
            adminService.resetInvites();
        return 0;
    }

    @RequestMapping(value = "/invite_create",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer invite_create(@RequestBody String object) throws UnsupportedEncodingException {
        ArrayList<String> req = splitURL.split(object);
        for (String iteration: req){
            adminService.inviteCreate(iteration);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        System.out.println(name);
        return 0;
    }

    @RequestMapping(value ="/users_get",produces = MediaType.APPLICATION_JSON_VALUE)
    public String users_get() throws JsonProcessingException {
        return adminService.findAllSpecialists();
    }

    @RequestMapping(value ="/get_dayspec",produces = MediaType.APPLICATION_JSON_VALUE)
    public String users_get(@RequestBody String object) throws UnsupportedEncodingException, JsonProcessingException {
        ArrayList<String> req = splitURL.split(object);
        System.out.println(dateService.returnAppointmentsSpecialist(req.get(1),req.get(0)));
        return dateService.returnAppointmentsSpecialist(req.get(1),req.get(0));
    }

    /*-- Даешь реализацию! --*/
    @RequestMapping(value ="/get_dayclient",produces = MediaType.APPLICATION_JSON_VALUE)
    public String get_dayclient(@RequestBody String object) throws UnsupportedEncodingException, JsonProcessingException {
        ArrayList<String> req = splitURL.split(object);
        System.out.println(dateService.returnAppointmentsUser(req.get(1),req.get(0)));
        return dateService.returnAppointmentsUser(req.get(1),req.get(0));
    }

    @RequestMapping(value = "/clear_users",  produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer clear_users(@RequestBody String object) throws UnsupportedEncodingException {
        ArrayList<String> req = splitURL.split(object);
        for(String i: req){
            System.out.println(i);
            dateService.deleteData(Integer.valueOf(i));
        }
        return 0;
    }

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

    @RequestMapping(value ="/delete_myDay",produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer delete_myDay(@RequestBody String object) throws UnsupportedEncodingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        ArrayList<String> req = splitURL.split(object);
        dateService.dayWithAppointmentsDelete(req.get(0), name);
        return 0;
    }

    @RequestMapping(value ="/get_myDay",produces = MediaType.APPLICATION_JSON_VALUE)
    public String get_myDay(@RequestBody String object) throws UnsupportedEncodingException, JsonProcessingException {
        ArrayList<String> req = splitURL.split(object);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        System.out.println(dateService.returnAppointmentsSpecialist(req.get(0),name));
        return dateService.returnAppointmentsSpecialist(req.get(0),name);
    }

}
