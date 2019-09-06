package ru.eltex.project.simpleappointer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.project.simpleappointer.dao.DateRepository;
import ru.eltex.project.simpleappointer.entities.Date;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Date service class
 * @author skwardlow
 * @version 1.0
 * @see Service
 */
@Slf4j
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
        log.info("Spec "+susername+" trying to find all appointments in day "+date);
        log.debug("Spec "+susername+" trying to find all appointments in day "+date+" found " +dates.toString());
        return objectMapper.writeValueAsString(dates);
    }

    /**
     * Search appointments for user method
     * @param date is current Date object for searching appointments
     * @param susername is current specialist username for searching appointments and free dates
     * @return json formed objects with appointments formed by date object
     * @throws JsonProcessingException
     */
    public String returnAppointmentsUser(String date, String susername, String authname) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Date> dates = new ArrayList<>();
        log.info("User" + susername + " trying to find appointments of" +susername+ " in date "+date);
        Integer index = 8;
        for (int i = 0; i<index;i++){
            if(dateRepository.existsByDateOfAppointmentAndIndexInDayAndSusername(date,i,susername)){
                dates.add(dateRepository.findByDateOfAppointmentAndSusernameAndIndexInDayOrderByIndexInDay
                        (date,susername,i));
                if(Objects.equals(dates.get(i).getCusername(), authname)){
                    dates.get(i).setCusername("Вы заняли этот прием");
                }else{
                    dates.get(i).setCusername("Занято");
                }
            }
            else dates.add(new Date(null,i,null,null))  ;
        }
        log.debug("User" + susername + " trying to find appointments of" +susername+ " in date "+date + " found "+dates.toString());
        return objectMapper.writeValueAsString(dates);
    }

    /**
     * Date with appointments delete method.
     * @param date is current Date object for searching appointments
     * @param susername is current Specialist username for deleting appointments
     */
    public void dayWithAppointmentsDelete(String date, String susername){
        dateRepository.deleteByDateOfAppointmentAndSusername(date,susername);
        log.warn("Spec "+susername+" deleting appointments in day " +date);
    }

    /**
     * Deleting only one appointment method
     * @param id is Appointment id for deleting from DB
     */
    public void deleteData(Integer id){
        log.warn("Appointment "+ dateRepository.findById(id).toString() + " deleted ");
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
            log.info("Client "+cusername+" registered to "+susername+" on "+date+" at ind "+dayIndex);
            return  true;
        }
        log.info("Client "+cusername+" failed reg to "+susername+" on "+date+" ind" +dayIndex);
        return false;
    }
}
