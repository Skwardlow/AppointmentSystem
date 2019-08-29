package ru.eltex.project.simpleappointer.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.project.simpleappointer.entities.Date;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface DateRepository extends CrudRepository<Date, Long> {
    ArrayList<Date> findAllByDateOfAppointmentAndCusername(String dateOf,String cusername);
    ArrayList<Date> findAllByDateOfAppointmentAndSusernameOrderByIndexInDay(String dateOf,String susername);
    ArrayList<Date> findAllByDateOfAppointment(String dateof);
    boolean existsByDateOfAppointmentAndIndexInDayAndSusernameAndCusername
            (String dateOfAppointment, Integer indexInDay, String susername, String cusernameString);
    @Transactional
    void deleteByDateOfAppointmentAndSusername(String dateof,String susername);
    @Transactional
    void deleteById(Integer id);

}
