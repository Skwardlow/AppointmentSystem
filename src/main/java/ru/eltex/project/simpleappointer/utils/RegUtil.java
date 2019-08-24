package ru.eltex.project.simpleappointer.utils;

import ru.eltex.project.simpleappointer.dao.SpecialistRepository;
import ru.eltex.project.simpleappointer.dao.UserRepository;
import ru.eltex.project.simpleappointer.entities.Specialist;
import ru.eltex.project.simpleappointer.entities.User;

public class RegUtil {
    public SpecialistRepository specialistRepository;
    public UserRepository userRepository;

    public byte regAddS(Specialist specialist){
        if (specialistRepository.existsByEmail(specialist.getEmail())){
            return 1;
        }
        if (specialistRepository.existsByLogin(specialist.getLogin())){
            return 2;
        }
        if (specialistRepository.existsByLoginAndEmail(specialist.getLogin(),specialist.getEmail())){
            return 3;
        }
        specialistRepository.save(specialist);
        return 0;
    }
    public void regAddU(User user){

    }

}