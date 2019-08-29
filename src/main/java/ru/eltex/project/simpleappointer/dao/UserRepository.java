package ru.eltex.project.simpleappointer.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.project.simpleappointer.entities.Role;
import ru.eltex.project.simpleappointer.entities.User;

import java.util.ArrayList;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String login);
    boolean existsByUsernameAndEmail(String login, String email);
    void deleteByUsername(String username);

    User findByUsername(String username);
    ArrayList<User> findAllByRoles(Set<Role> roles);
}