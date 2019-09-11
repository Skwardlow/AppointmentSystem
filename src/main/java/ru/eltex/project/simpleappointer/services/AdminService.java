package ru.eltex.project.simpleappointer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.project.simpleappointer.dao.InviteRepository;
import ru.eltex.project.simpleappointer.dao.UserRepository;
import ru.eltex.project.simpleappointer.entities.Invite;
import ru.eltex.project.simpleappointer.entities.Role;
import ru.eltex.project.simpleappointer.entities.User;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Admin service class
 *
 * @author skwardlow
 * @version 1.0
 * @see Service
 */
@Slf4j
@Service
public class AdminService {
    /**
     * Dao user repository
     */
    @Autowired
    UserRepository userRepository;
    /**
     * dao invite repository
     */
    @Autowired
    InviteRepository inviteRepository;

    /**
     * Deleting user method
     *
     * @param username is field username for searching User object
     * @see User
     */
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
        log.warn("Deleting user" + username);
    }

    /**
     * Resetting invites method. Deleting all invites in db
     */
    public void resetInvites() {
        inviteRepository.deleteAll();
        log.warn("Deleting all users (init by admin)");
    }

    /**
     * Method for creating new invite
     *
     * @param inviteName identify for new invite
     * @see Invite
     */
    public void inviteCreate(String inviteName) {
        Invite invite = new Invite();
        invite.setIdentify(inviteName);
        inviteRepository.save(invite);
        log.info("Creating new invite");
        log.debug("Creating invite " + inviteName);
    }

    /**
     * Searching for Specialist method.
     *
     * @return json formed user objects
     * @throws JsonProcessingException
     * @see User
     */
    public String findAllSpecialists() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<User> users = new ArrayList<>();
        ArrayList<User> usersREADY = new ArrayList<>();
        userRepository.findAllByRoles(Collections.singleton(Role.SPECIALIST)).forEach(users::add);
        for (User user : users) {
            user.setPassword(null);
            usersREADY.add(user);
        }
        log.info("Searching for all specialists");
        log.debug("Searching for specialists return: " + mapper.writeValueAsString(usersREADY));
        return mapper.writeValueAsString(usersREADY);
    }
}
