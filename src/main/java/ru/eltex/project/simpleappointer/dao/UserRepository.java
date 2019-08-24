package ru.eltex.project.simpleappointer.dao;

import org.springframework.stereotype.Repository;
import ru.eltex.project.simpleappointer.entities.User;

@Repository
public interface UserRepository extends CommonRepository<User> {
    boolean existsByEmail(String email);
    boolean existsByLogin(String login);
    boolean existsByLoginAndEmail(String login, String email);
}
