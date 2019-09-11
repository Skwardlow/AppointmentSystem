package ru.eltex.project.simpleappointer.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.eltex.project.simpleappointer.dao.InviteRepository;
import ru.eltex.project.simpleappointer.dao.UserRepository;
import ru.eltex.project.simpleappointer.entities.Role;
import ru.eltex.project.simpleappointer.entities.User;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

/**
 * User service class
 *
 * @author skwardlow
 * @see UserDetailsService
 */
@Service
@Slf4j
public class UserService implements UserDetailsService {
    /**
     * Dao user repository
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * Dao Invite repository
     */
    @Autowired
    private InviteRepository inviteRepository;

    /**
     * Registration user method
     *
     * @param user   is user object created using constructor
     * @param invite is invite object created using constructor
     * @return reg answer. 1- email exists, 2 username exists
     * 3 - username and email exists, 4 identify of invite exists if specified
     * 0 if registration successful
     */
    @Transactional
    public byte regAddU(User user, String invite) {
        if (userRepository.existsByEmail(user.getEmail())) {
            log.info("Tried reg with existing email " + user.getEmail());
            return 1;
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            log.info("Tried reg with existing username " + user.getUsername());
            return 2;
        }
        if (userRepository.existsByUsernameAndEmail(user.getUsername(), user.getEmail())) {
            log.info("Tried reg with existing username " + user.getUsername() + " and email " + user.getEmail());
            return 3;
        }
        if ((!inviteRepository.existsByIdentify(invite)) && (invite != null)) {
            log.info("User " + user.getUsername() + " tried broken invite " + invite);
            return 4;
        }
        if ((inviteRepository.existsByIdentify(invite)) && (invite != null)) {
            user.setRoles(Collections.singleton(Role.SPECIALIST));
            userRepository.save(user);
            inviteRepository.deleteByIdentify(invite);
            user.setActive(true);
            log.info("Specialist " + user.getUsername() + " " + user.getEmail() + " registered using" + invite);
            return 0;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        log.info("Client " + user.getUsername() + " " + user.getEmail() + " registered");
        return 0;
    }

    /**
     * load user by username automatically generated method
     *
     * @param username is String with username
     * @return user object if founded in DB
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Trying to find " + username + "in DB");
        return userRepository.findByUsername(username);

    }
}
