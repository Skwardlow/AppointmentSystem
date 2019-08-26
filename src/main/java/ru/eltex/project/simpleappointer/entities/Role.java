package ru.eltex.project.simpleappointer.entities;

import javax.persistence.*;
import java.util.Set;

@lombok.Setter
@lombok.Getter

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany
    private Set<User> users;


}
