package ru.eltex.project.simpleappointer.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eltex.project.simpleappointer.dao.InviteRepository;
import ru.eltex.project.simpleappointer.dao.SpecialistRepository;
import ru.eltex.project.simpleappointer.dao.UserRepository;
import ru.eltex.project.simpleappointer.entities.Specialist;
import ru.eltex.project.simpleappointer.entities.User;

import java.util.List;

@Component
public class RegUtil {
    @Autowired
    public SpecialistRepository specialistRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public InviteRepository inviteRepository;


    public byte regAddS(Specialist specialist, String invite){
        if (specialistRepository.existsByLoginAndEmail(specialist.getLogin(),specialist.getEmail())){
            return 3;
        }
        if (specialistRepository.existsByLogin(specialist.getLogin())){
            return 2;
        }
        if (!inviteRepository.existsByIdentify(invite)){
            return 4;
        }
        if (specialistRepository.existsByEmail(specialist.getEmail())){
            return 1;
        }
        specialistRepository.save(specialist);
        return 0;
    }
    public byte regAddU(User user){
        if (userRepository.existsByEmail(user.getEmail())){
            return 1;
        }
        if (userRepository.existsByLogin(user.getLogin())){
            return 2;
        }
        if (userRepository.existsByLoginAndEmail(user.getLogin(),user.getEmail())){
            return 3;
        }
        userRepository.save(user);
        return 0;
    }
}