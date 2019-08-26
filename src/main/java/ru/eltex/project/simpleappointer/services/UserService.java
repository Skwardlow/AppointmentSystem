package ru.eltex.project.simpleappointer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.eltex.project.simpleappointer.dao.RoleRepository;
import ru.eltex.project.simpleappointer.dao.UserRepository;
import ru.eltex.project.simpleappointer.entities.Role;
import ru.eltex.project.simpleappointer.entities.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService extends AbstractService<User, UserRepository> {
    /*@Autowired
    public UserService(UserRepository repository){
        super(repository);
    }*/
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public byte regAddU(User user){
        if (userRepository.existsByEmail(user.getEmail())){
            return 1;
        }
        if (userRepository.existsByLogin(user.getLogin())){
            return 2;
        }
        if (userRepository.existsByLoginAndEmail(user.getLogin(),user.getEmail())){
            return 3;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<Role>((Collection<? extends Role>) roleRepository.findAll()));
        userRepository.save(user);
        return 0;
    }

    public void create(User user){
        userRepository.save(user);
    }

    public User readById(Long id){
        if (userRepository.findById(id).isPresent()){
            return userRepository.findById(id).get();
        }
        return null;
    }

    public List<User> readAll(){
        return (List<User>) userRepository.findAll();
    }

    public void update(User user){
        userRepository.save(user);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public User findByUsername(String username){
        return userRepository.findByLogin(username);
    }
}
