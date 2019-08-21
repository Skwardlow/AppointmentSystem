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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String phone;
    private String email;

}
