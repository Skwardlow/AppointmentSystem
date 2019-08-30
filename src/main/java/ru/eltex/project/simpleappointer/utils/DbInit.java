package ru.eltex.project.simpleappointer.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.eltex.project.simpleappointer.dao.UserRepository;
import ru.eltex.project.simpleappointer.entities.Role;
import ru.eltex.project.simpleappointer.entities.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.Properties;

@Component
public class DbInit implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        InputStream input = new FileInputStream("src/main/resources/template.properties");
        Properties prop = new Properties();
        prop.load(input);


        try(InputStream in = new URL(prop.getProperty("background-image")).openStream()){
            Files.copy(in, Paths.get("src/main/resources/static/img.jpg"), StandardCopyOption.REPLACE_EXISTING);
        }

        User user = new User
                ("admin", "admin", "admin", "admin", prop.getProperty("admin-password"));
        user.setRoles(Collections.singleton(Role.ADMIN));
        user.setActive(true);
        if (!userRepository.existsByUsername("admin")) {
            userRepository.save(user);
        }
        else {
            userRepository.deleteByUsername("admin");
            userRepository.save(user);
        }

    }
}
