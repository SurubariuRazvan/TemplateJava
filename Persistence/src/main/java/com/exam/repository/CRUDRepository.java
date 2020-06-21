package com.exam.repository;

import com.exam.domain.Entity;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface CRUDRepository<E extends Entity<ID>, ID extends Serializable> {
    E findByID(ID id);

    Iterable<E> findAll();

    E save(E entity);

    E update(E entity);

    E delete(ID id);
}
