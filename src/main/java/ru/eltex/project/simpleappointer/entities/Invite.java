package ru.eltex.project.simpleappointer.entities;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@lombok.Getter
@lombok.Setter
@Entity
@NoArgsConstructor
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    String identify;

    Invite(String identify){
        this.identify = identify;
    }

}
