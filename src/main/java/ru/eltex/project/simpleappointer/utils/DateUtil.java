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
    public String returnAppointment(String date) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Date> dates = new ArrayList<>();
        dateRepository.findAll().forEach(dates::add);
        return objectMapper.writeValueAsString(dates);
    }
}
