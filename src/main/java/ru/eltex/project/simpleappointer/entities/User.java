package ru.eltex.project.simpleappointer.entities;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@lombok.Setter
@lombok.Getter

@Entity
@NoArgsConstructor
public class User extends Parent {
    private String address;

    public User(String name, String phone, String email,String login, String password){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.login = login;
        this.password = password;
    }
}
