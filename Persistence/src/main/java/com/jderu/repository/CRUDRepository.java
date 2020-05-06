package com.jderu.repository;

import com.jderu.domain.Entity;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface CRUDRepository<E extends Entity<ID>, ID extends Serializable> {
    E findByID(ID id);

    Iterable<E> findAll();

    /**
     * entity with id null is save, other are update
     *
     * @param entity with id
     * @return old entity if it updated or null if it saved
     */
    E save(E entity);

    /**
     * @param id of an entity
     * @return entity if it deleted, null otherwise
     */
    E delete(ID id);
}
