package ru.eltex.project.simpleappointer.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eltex.project.simpleappointer.services.AdminService;
import ru.eltex.project.simpleappointer.services.DateService;
import ru.eltex.project.simpleappointer.utils.SplitURL;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * An REST-controller for mapping POST admin requests to the server
 *
 * @author Aaaaa988, skwardlow
 * @version 1.0
 * @see RestController
 */
@RestController
public class AdminController {
    /**
     * Wiring Admin service for DAO interaction
     */
    @Autowired
    AdminService adminService;
    /**
     * Wiring Date service for DAO interaction
     */
    @Autowired
    DateService dateService;
    /**
     * Wiring URL util for splitting incoming requests
     */
    @Autowired
    SplitURL splitURL;

    /**
     * Deleting specialist from DB method
     *
     * @param object url not splitted body, coming to spliturl util, contains username of spec
     * @return 0, success ans for checking connection between server and client
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/delete_spec", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer delete_spec(@RequestBody String object) throws UnsupportedEncodingException {
        ArrayList<String> req = splitURL.split(object);
        adminService.deleteByUsername(req.get(0));
        return 0;
    }

    /**
     * Deleting day with appointments from DB method
     *
     * @param object url not splitted body, coming to spliturl util, contains spec uname and day in format '30/02/19'
     * @return 0, success ans for checking connection between server and client
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/delete_day", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer delete_day(@RequestBody String object) throws UnsupportedEncodingException {
        ArrayList<String> req = splitURL.split(object);
        dateService.dayWithAppointmentsDelete(req.get(0), req.get(1));
        return 0;
    }

    /**
     * Invites resetting method, deleting all unused invites in DB
     *
     * @return 0, success ans for checking connection between server and client
     */
    @RequestMapping(value = "/invite_reset", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer invite_reset() {
        adminService.resetInvites();
        return 0;
    }

    /**
     * Creating new invite(s) method, inserting new invites in db
     *
     * @param object url not splitted body, contains invite identifies
     * @return 0, success ans for checking connection between server and client
     * @throws UnsupportedEncodingException
     * @see ru.eltex.project.simpleappointer.entities.Invite
     */
    @RequestMapping(value = "/invite_create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer invite_create(@RequestBody String object) throws UnsupportedEncodingException {
        ArrayList<String> req = splitURL.split(object);
        for (String iteration : req) {
            adminService.inviteCreate(iteration);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        System.out.println(name);
        return 0;
    }

    /**
     * Request fot all specialist method
     *
     * @return json formed specialist objects from DB
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/users_get", produces = MediaType.APPLICATION_JSON_VALUE)
    public String users_get() throws JsonProcessingException {
        return adminService.findAllSpecialists();
    }


}
