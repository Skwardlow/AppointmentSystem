package ru.eltex.project.simpleappointer.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.eltex.project.simpleappointer.dao.InviteRepository;
import ru.eltex.project.simpleappointer.dao.UserRepository;
import ru.eltex.project.simpleappointer.entities.Invite;

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
}
