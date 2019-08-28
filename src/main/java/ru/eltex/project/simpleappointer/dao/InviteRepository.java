package ru.eltex.project.simpleappointer.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.project.simpleappointer.entities.Invite;

@Repository
public interface InviteRepository extends CrudRepository<Invite,Long> {
    boolean existsByIdentify(String identify);
    String deleteByIdentify(String identify);
}
