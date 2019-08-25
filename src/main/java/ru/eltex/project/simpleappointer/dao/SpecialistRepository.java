package ru.eltex.project.simpleappointer.dao;

import org.springframework.stereotype.Repository;
import ru.eltex.project.simpleappointer.entities.Specialist;

@Repository
public interface SpecialistRepository extends CommonRepository<Specialist> {
    boolean existsByEmail(String email);
    boolean existsByLogin(String login);
    boolean existsByLoginAndEmail(String login, String email);

}
