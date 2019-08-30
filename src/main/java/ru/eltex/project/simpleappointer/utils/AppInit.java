package ru.eltex.project.simpleappointer.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.eltex.project.simpleappointer.dao.UserRepository;
import ru.eltex.project.simpleappointer.entities.Role;
import ru.eltex.project.simpleappointer.entities.User;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.Properties;

@Component
public class AppInit implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        InputStream input = new FileInputStream("src/main/resources/template.properties");
        Properties prop = new Properties();
        prop.load(input);


        InputStream in = new URL(prop.getProperty("background-image")).openStream();
        Files.copy(in, Paths.get("src/main/resources/static/contents/img/Promo_one.jpg"), StandardCopyOption.REPLACE_EXISTING);
        in = new URL(prop.getProperty("logo-image")).openStream();
        Files.copy(in, Paths.get("src/main/resources/static/contents/img/Logo.png"), StandardCopyOption.REPLACE_EXISTING);
        in = new URL(prop.getProperty("registration-back")).openStream();
        Files.copy(in, Paths.get("src/main/resources/static/contents/img/Promo_two.jpg"), StandardCopyOption.REPLACE_EXISTING);
        in = new URL(prop.getProperty("work-back")).openStream();
        Files.copy(in, Paths.get("src/main/resources/static/contents/img/Promo_back.jpg"), StandardCopyOption.REPLACE_EXISTING);
        in = new URL(prop.getProperty("left-column")).openStream();
        Files.copy(in, Paths.get("src/main/resources/static/contents/img/Promo_left_col.jpg"), StandardCopyOption.REPLACE_EXISTING);
        in = new URL(prop.getProperty("right-column")).openStream();
        Files.copy(in, Paths.get("src/main/resources/static/contents/img/Promo_right_col.jpg"), StandardCopyOption.REPLACE_EXISTING);

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
