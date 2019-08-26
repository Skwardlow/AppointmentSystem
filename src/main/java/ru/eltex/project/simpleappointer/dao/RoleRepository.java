package ru.eltex.project.simpleappointer.dao;

import org.springframework.data.repository.CrudRepository;
import ru.eltex.project.simpleappointer.entities.Role;

public interface RoleRepository extends CrudRepository<Role,Long> {
}
