package ru.eltex.project.simpleappointer.services;

import org.springframework.stereotype.Service;
import ru.eltex.project.simpleappointer.dao.UserRepository;
import ru.eltex.project.simpleappointer.entities.User;

@Service
public class UserService extends AbstractService<User, UserRepository> {

    public UserService(UserRepository repository){
        super(repository);
    }
}
