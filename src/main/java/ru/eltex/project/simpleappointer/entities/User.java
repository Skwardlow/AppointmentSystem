package ru.eltex.project.simpleappointer.entities;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@lombok.Setter
@lombok.Getter

@Entity
@NoArgsConstructor
public class User extends Parent {
    private String address;
}
