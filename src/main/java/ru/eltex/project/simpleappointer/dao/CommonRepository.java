package ru.eltex.project.simpleappointer.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.eltex.project.simpleappointer.entities.Parent;

@NoRepositoryBean
public interface CommonRepository<E extends Parent> extends CrudRepository<E,Long> {
}
