package ru.eltex.project.simpleappointer.services;

import org.springframework.stereotype.Service;
import ru.eltex.project.simpleappointer.dao.SpecialistRepository;
import ru.eltex.project.simpleappointer.entities.Specialist;

@Service
public class SpecialistService extends AbstractService<Specialist, SpecialistRepository> {

    public SpecialistService(SpecialistRepository repository){
        super(repository);
    }
}
