package ru.eltex.project.simpleappointer.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.project.simpleappointer.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String login);
    boolean existsByUsernameAndEmail(String login, String email);
    void deleteByUsername(String username);

    User findByUsername(String username);
}