package ru.eltex.project.simpleappointer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.project.simpleappointer.dao.DateRepository;
import ru.eltex.project.simpleappointer.entities.Date;

import java.util.ArrayList;

/**
 * Date service class
 * @author skwardlow
 * @version 1.0
 * @see Service
 */
@Service
public class DateService {
    /**
     * Dao date repository
     */
    @Autowired
    DateRepository dateRepository;

    /**
     * Search appointments for specialist method
     * @param date is current Date object for searching appointments
     * @param susername is current specialist username for searching appointments
     * @return json formed objects with appointments formed by date object
     * @throws JsonProcessingException
     */
    public String returnAppointmentsSpecialist(String date,String susername) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Date> dates = new ArrayList<>();
        dateRepository.findAllByDateOfAppointmentAndSusernameOrderByIndexInDay(date,susername).forEach(dates::add);
        return objectMapper.writeValueAsString(dates);
    }

    /**
     * Search appointments for user method
     * @param date is current Date object for searching appointments
     * @param susername is current specialist username for searching appointments and free dates
     * @return json formed objects with appointments formed by date object
     * @throws JsonProcessingException
     */
    public String returnAppointmentsUser(String date, String susername) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Date> dates = new ArrayList<>();
        Integer index = 8;
        for (int i = 0; i<index;i++){
            if(dateRepository.existsByDateOfAppointmentAndIndexInDayAndSusername(date,i,susername)){
                dates.add(dateRepository.findByDateOfAppointmentAndSusernameAndIndexInDayOrderByIndexInDay
                        (date,susername,i));
            }
            else dates.add(new Date(null,i,null,null))  ;
        }
        return objectMapper.writeValueAsString(dates);
    }

    /**
     * Date with appointments delete method.
     * @param date is current Date object for searching appointments
     * @param susername is current Specialist username for deleting appointments
     */
    public void dayWithAppointmentsDelete(String date, String susername){
        dateRepository.deleteByDateOfAppointmentAndSusername(date,susername);
    }

    /**
     * Deleting only one appointment method
     * @param id is Appointment id for deleting from DB
     */
    public void deleteData(Integer id){
        dateRepository.deleteById(id);
    }

    /**
     * Registering new appointment method.
     * @param date Appointment date object
     * @param dayIndex Index in day param
     * @param susername Specialist username
     * @param cusername Client username
     * @return boolean where true - successful registration, false - error
     * @see Date
     */
    public boolean writeData(String date, Integer dayIndex,String susername, String cusername){
        if (!dateRepository.existsByDateOfAppointmentAndCusernameAndSusername(date,cusername,susername)) {
            dateRepository.save(new Date(date,dayIndex,susername,cusername));
            return  true;
        }
        return false;
    }
}
