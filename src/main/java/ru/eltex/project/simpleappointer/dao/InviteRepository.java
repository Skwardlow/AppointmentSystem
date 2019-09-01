package ru.eltex.project.simpleappointer.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.project.simpleappointer.entities.Invite;

import javax.transaction.Transactional;
/**
 * DAO repository for Invite objects
 * @author skwardlow
 * @version 1.0
 * @see Repository
 * @see CrudRepository
 * @see Invite
 */
@Repository
public interface InviteRepository extends CrudRepository<Invite,Long> {
    /**
     * Checking existence of invite by:
     * @param identify generated identify field in Invite object
     * @return boolean where 1 - exist, 0 - not exits
     */
    boolean existsByIdentify(String identify);

    /**
     * Deleting method where Invite finding by:
     * @param identify generated identify field in Invite object
     */
    @Transactional
    void deleteByIdentify(String identify);
}
