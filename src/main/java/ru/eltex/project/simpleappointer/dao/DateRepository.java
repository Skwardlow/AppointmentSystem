package ru.eltex.project.simpleappointer.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.project.simpleappointer.entities.Date;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface DateRepository extends CrudRepository<Date, Long> {
    ArrayList<Date> findAllByDateOfAppointment(String dateof);

    ArrayList<Date> findAllByDateOfAppointmentAndSusernameOrderByIndexInDay(String dateOf,String susername);

    Date findByDateOfAppointmentAndSusernameAndIndexInDayOrderByIndexInDay
            (String dateof, String susername, Integer index);

    boolean existsByDateOfAppointmentAndIndexInDayAndSusernameAndCusername
            (String dateOfAppointment, Integer indexInDay, String susername, String cusernameString);

    boolean existsByDateOfAppointmentAndIndexInDayAndSusername(String date, Integer index, String susername);

    boolean existsByDateOfAppointmentAndCusernameAndSusername(String date, String cusername, String susername);

    @Transactional
    void deleteByDateOfAppointmentAndSusername(String dateof,String susername);
    @Transactional
    void deleteById(Integer id);

}
