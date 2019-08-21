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
}
