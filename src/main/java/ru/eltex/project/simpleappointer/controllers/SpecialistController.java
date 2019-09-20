package ru.eltex.project.simpleappointer.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eltex.project.simpleappointer.services.DateService;
import ru.eltex.project.simpleappointer.utils.SplitURL;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * An REST-controller for mapping POST Specialist requests to the server
 *
 * @author Aaaaa988, skwardlow
 * @version 1.0
 * @see RestController
 */
@RestController
public class SpecialistController {
    /**
     * Wiring date service
     */
    @Autowired
    DateService dateService;

    /**
     * Clearing appointments(dates) in day method
     *
     * @param object Url body, contains date object
     * @return 0, success ans for checking connection between server and user
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/clear_users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer clear_users(@RequestBody String object) throws UnsupportedEncodingException {
        ArrayList<String> req = SplitURL.split(object);
        for (String i : req) {
            dateService.deleteData(Integer.valueOf(i));
        }
        return 0;
    }

    /**
     * Getting dates in day for spec method
     *
     * @param object not splitted URL, contains specialist name and date (ex '30/02/19')
     * @return json formed object, that contains date objects
     * @throws UnsupportedEncodingException
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/get_dayspec", produces = MediaType.APPLICATION_JSON_VALUE)
    public String users_get(@RequestBody String object) throws UnsupportedEncodingException, JsonProcessingException {
        ArrayList<String> req = SplitURL.split(object);
        return dateService.returnAppointmentsSpecialist(req.get(1), req.get(0));
    }

    /**
     * Deleting specialist day method
     *
     * @param object not splitted URL, contains specialist name and date (ex '30/02/2019')
     * @return 0, success ans for checking connection between server and user
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/delete_myDay", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer delete_myDay(@RequestBody String object) throws UnsupportedEncodingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        ArrayList<String> req = SplitURL.split(object);
        dateService.dayWithAppointmentsDelete(req.get(0), name);
        return 0;
    }

    /**
     * This method is getting appointments in day for specialist
     *
     * @param object not splitted url, contains specialist name and appointment day ( ex. '30/02/19')
     * @return json formed object with appointment objects
     * @throws UnsupportedEncodingException
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/get_myDay", produces = MediaType.APPLICATION_JSON_VALUE)
    public String get_myDay(@RequestBody String object) throws UnsupportedEncodingException, JsonProcessingException {
        ArrayList<String> req = SplitURL.split(object);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return dateService.returnAppointmentsSpecialist(req.get(0), name);
    }
}
