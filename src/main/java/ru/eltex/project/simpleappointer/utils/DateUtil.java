package ru.eltex.project.simpleappointer.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eltex.project.simpleappointer.dao.DateRepository;
import ru.eltex.project.simpleappointer.entities.Date;

import java.util.ArrayList;

@Component
public class DateUtil {
    @Autowired
    DateRepository dateRepository;
    /**oh my*/
    public String returnAppointmentsSpecialist(String date,String susername) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Date> dates = new ArrayList<>();
        dateRepository.findAllByDateOfAppointmentAndSusernameOrderByIndexInDay(date,susername).forEach(dates::add);
        return objectMapper.writeValueAsString(dates);
    }

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

    public String returnAppointmentsAdmin(String date) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Date> dates = new ArrayList<>();
        dateRepository.findAllByDateOfAppointment(date).forEach(dates::add);
        return objectMapper.writeValueAsString(dates);
    }

    public boolean apppointmentCreate(String date, Integer dayIndex, String susername, String cusername){
        if (dateRepository.existsByDateOfAppointmentAndIndexInDayAndSusernameAndCusername
                (date,dayIndex,susername,cusername)){return false;}
        Date appointment = new Date(date,dayIndex,susername,cusername);
        dateRepository.save(appointment);
        return true;
    }

    public void dayWithAppointmentsDelete(String date, String susername){
        dateRepository.deleteByDateOfAppointmentAndSusername(date,susername);
    }

    public void deleteData(Integer id){
        dateRepository.deleteById(id);
    }


}