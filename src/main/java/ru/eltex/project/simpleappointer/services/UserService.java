package ru.eltex.project.simpleappointer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.project.simpleappointer.dao.UserRepository;
import ru.eltex.project.simpleappointer.entities.User;

import java.util.List;

@Service
public class UserService extends AbstractService<User, UserRepository> {
    /*@Autowired
    public UserService(UserRepository repository){
        super(repository);
    }*/
    @Autowired
    private UserRepository repository;

    public byte regAddU(User user){
        if (repository.existsByEmail(user.getEmail())){
            return 1;
        }
        if (repository.existsByLogin(user.getLogin())){
            return 2;
        }
        if (repository.existsByLoginAndEmail(user.getLogin(),user.getEmail())){
            return 3;
        }
        repository.save(user);
        return 0;
    }

    public void create(User user){
        repository.save(user);
    }

    public User readById(Long id){
        if (repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }
        return null;
    }

    public List<User> readAll(){
        return (List<User>) repository.findAll();
    }

    public void update(User user){
        repository.save(user);
    }

    public void delete(User user){
        repository.delete(user);
    }
}
