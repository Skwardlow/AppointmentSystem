package ru.eltex.project.simpleappointer.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.project.simpleappointer.entities.Date;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * DAO repository for Date objects
 * @author skwardlow
 * @version 1.0
 * @see Repository
 * @see CrudRepository
 * @see Date
 */
@Repository
public interface DateRepository extends CrudRepository<Date, Long> {
    /**
     * Searching for all appointments in day for specialist
     * @param dateOf date FIELD ex '30/02/19'
     * @param susername Specialist username field
     * @return ArrayList of Date objects
     */
    ArrayList<Date> findAllByDateOfAppointmentAndSusernameOrderByIndexInDay(String dateOf,String susername);

    /**
     * Finding only one appointment by date, spec.username and day index
     * @param dateof date of appointment ex '30/02/19'
     * @param susername Specialist username
     * @param index Index in day field
     * @return Date
     */
    Date findByDateOfAppointmentAndSusernameAndIndexInDayOrderByIndexInDay
            (String dateof, String susername, Integer index);

    /**
     * Checking existence of appointment by:
     * @param date date of appointment ex '30/02/19'
     * @param index index in day
     * @param susername specialist username
     * @return boolean where 1 - exist, 0 - not exits
     */
    boolean existsByDateOfAppointmentAndIndexInDayAndSusername(String date, Integer index, String susername);

    /**
     * Checking existence of Appointment by:
     * @param date date of appointment ex '30/02/19'
     * @param cusername Client username
     * @param susername Specialist username
     * @return boolean where 1 - exist, 0 - not exits
     */
    boolean existsByDateOfAppointmentAndCusernameAndSusername(String date, String cusername, String susername);

    /**
     * Deleting date object finding by:
     * @param dateof date of appointment ex '30/02/19'
     * @param susername specialist username
     */
    @Transactional
    void deleteByDateOfAppointmentAndSusername(String dateof,String susername);

    /**
     * Deleting date object by:
     * @param id is id of Appointment
     */
    @Transactional
    void deleteById(Integer id);

}
