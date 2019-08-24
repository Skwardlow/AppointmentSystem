package ru.eltex.project.simpleappointer.entities;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@lombok.Setter
@lombok.Getter

@Entity
@NoArgsConstructor
public class Specialist extends Parent {
    private String aboutMe;
    private Date experienceOf;

    public Specialist(String name, String phone, String email,String login, String password){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.login = login;
        this.password = password;
    }

}
