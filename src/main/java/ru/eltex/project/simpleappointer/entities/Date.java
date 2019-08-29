package ru.eltex.project.simpleappointer.entities;


import lombok.NoArgsConstructor;

import javax.persistence.*;

@lombok.Setter
@lombok.Getter

@Entity
@Table(name ="date")
@NoArgsConstructor
public class Date {
    @Transient
    private Integer beginWorkDayHour = 9;
    @Transient
    private Integer gradTimeOfAppointment = 60;
    @Transient
    private Integer amountOfAppointment = 8;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String dateOfAppointment;
    private Integer indexInDay;
    private String susername;
    private String cusername;

    public Date(String dateOfAppointment, Integer indexInDay, String susername, String cusername) {
        this.dateOfAppointment = dateOfAppointment;
        this.indexInDay = indexInDay;
        this.susername = susername;
        this.cusername = cusername;
    }


}
