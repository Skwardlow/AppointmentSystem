package ru.eltex.project.simpleappointer.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.eltex.project.simpleappointer.dao.CommonRepository;
import ru.eltex.project.simpleappointer.entities.Parent;

public abstract class AbstractService<E extends Parent, R extends CommonRepository<E>>{
    /*@Autowired
    protected final R repository;


    public AbstractService(R repository){
        this.repository = repository;
    }*/
}
