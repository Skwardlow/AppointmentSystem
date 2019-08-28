package ru.eltex.project.simpleappointer.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.eltex.project.simpleappointer.dao.UserRepository;
import ru.eltex.project.simpleappointer.entities.Role;
import ru.eltex.project.simpleappointer.entities.User;

import java.util.Collections;
@Component
public class DbInit implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        User user = new User("admin","admin","admin","admin","qwe12345");
        user.setRoles(Collections.singleton(Role.ADMIN));
        user.setActive(true);
         if (!userRepository.existsByUsername("admin")) {userRepository.save(user);}
    }
}
