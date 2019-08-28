package ru.eltex.project.simpleappointer.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.eltex.project.simpleappointer.dao.InviteRepository;
import ru.eltex.project.simpleappointer.dao.UserRepository;
import ru.eltex.project.simpleappointer.entities.Invite;
import ru.eltex.project.simpleappointer.entities.User;

import java.util.ArrayList;

@Component
@Transactional
public class AdminUtils {
    @Autowired
    UserRepository userRepository;
    @Autowired
    InviteRepository inviteRepository;

    public void deleteByUsername(String username){
        userRepository.deleteByUsername(username);
    }

    public void resetInvites(){
        inviteRepository.deleteAll();
    }

    public void inviteCreate(String inviteName){
        Invite invite = new Invite();
        invite.setIdentify(inviteName);
        inviteRepository.save(invite);
    }

    public String findAllUsers() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return mapper.writeValueAsString(users);
    }
}
