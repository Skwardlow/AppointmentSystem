package ru.eltex.project.simpleappointer.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.project.simpleappointer.entities.Role;
import ru.eltex.project.simpleappointer.entities.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Set;
/**
 * DAO repository for User objects
 * @author skwardlow
 * @version 1.0
 * @see Repository
 * @see CrudRepository
 * @see User
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    /**
     * Checking existence of user by:
     * @param email email field in User object
     * @return boolean where 1 - exist, 0 - not exits
     */
    boolean existsByEmail(String email);
    /**
     * Checking existence of user by:
     * @param login login field in User object
     * @return boolean where 1 - exist, 0 - not exits
     */
    boolean existsByUsername(String login);
    /**
     * Checking existence of user by:
     * @param email email field in User object
     * @param login login field in User object
     * @return boolean where 1 - exist, 0 - not exits
     */
    boolean existsByUsernameAndEmail(String login, String email);
    /**
     * Deleting User from db by:
     * @param username is username field in User object
     */
    @Transactional
    void deleteByUsername(String username);

    /**
     * Searching user object in db by:
     * @param username username field in User object
     * @return user object
     */
    User findByUsername(String username);

    /**
     * Finding all users by:
     * @param roles Set of Roles by you want to search
     * @return Arraylist of users
     */
    ArrayList<User> findAllByRoles(Set<Role> roles);
}