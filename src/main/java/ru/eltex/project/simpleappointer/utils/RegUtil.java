package ru.eltex.project.simpleappointer.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eltex.project.simpleappointer.dao.InviteRepository;
import ru.eltex.project.simpleappointer.dao.UserRepository;
import ru.eltex.project.simpleappointer.entities.Role;
import ru.eltex.project.simpleappointer.entities.User;

import java.util.Collections;

@Component
public class RegUtil {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public InviteRepository inviteRepository;

    public byte regAddU(User user, String invite){
        if (userRepository.existsByEmail(user.getEmail())){
            return 1;
        }
        if (userRepository.existsByUsername(user.getUsername())){
            return 2;
        }
        if (userRepository.existsByUsernameAndEmail(user.getUsername(),user.getEmail())){
            return 3;
        }
        if(!inviteRepository.existsByIdentify(invite)){
            return 4;
        }
        if((inviteRepository.existsByIdentify(invite))&&(invite!=null)){
            user.setRoles(Collections.singleton(Role.SPECIALIST));
            userRepository.save(user);
            inviteRepository.deleteByIdentify(invite);
            user.setActive(true);
            return 0;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return 0;
    }
}