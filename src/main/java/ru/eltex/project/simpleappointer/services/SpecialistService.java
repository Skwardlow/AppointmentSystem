package ru.eltex.project.simpleappointer.services;

import org.springframework.stereotype.Service;
import ru.eltex.project.simpleappointer.dao.SpecialistRepository;
import ru.eltex.project.simpleappointer.entities.Specialist;

import java.util.List;

@Service
public class SpecialistService extends AbstractService<Specialist, SpecialistRepository> {

    public SpecialistService(SpecialistRepository repository){
        super(repository);
    }

    public void create(Specialist specialist){
        repository.save(specialist);
    }

    public Specialist readById(Long id){
        if (repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }
        return null;
    }

    public List<Specialist> readAll(){
        new Lists.newArrayList(repository.findAll());
    }

    public void update(Specialist specialist){
        repository.save(specialist);
    }

    public void delete(Specialist specialist){
        repository.delete(specialist);
    }
}
