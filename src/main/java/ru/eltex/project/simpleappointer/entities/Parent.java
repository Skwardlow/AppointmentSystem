package ru.eltex.project.simpleappointer.entities;

import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@lombok.Setter
@lombok.Getter

@MappedSuperclass
@NoArgsConstructor
public abstract class Parent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    protected String name;
    protected String phone;
    protected String email;

    protected String login;
    protected String password;

}
