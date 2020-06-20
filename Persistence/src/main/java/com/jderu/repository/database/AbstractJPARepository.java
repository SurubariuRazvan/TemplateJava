package com.jderu.repository.database;

import com.jderu.domain.Entity;
import com.jderu.repository.CRUDRepository;
import com.jderu.validation.CRUDValidator;
import com.jderu.validation.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public abstract class AbstractJPARepository<E extends Entity<ID>, ID extends Serializable> implements CRUDRepository<E, ID> {
    protected static final Logger logger = LogManager.getLogger();
    protected final CRUDValidator<E> validator;
    protected final Class<E> entityType;
    protected final CrudRepository<E, ID> repo;

    AbstractJPARepository(CRUDValidator<E> validator, Class<E> entityType, CrudRepository<E, ID> repo) {
        this.validator = validator;
        this.entityType = entityType;
        this.repo = repo;
    }

    @Override
    public E findByID(ID id) {
        if (id == null)
            throw new IllegalArgumentException("id is null");
        logger.info("Find " + entityType.getName() + " by id: " + id);
        return repo.findById(id).orElse(null);
    }

    @Override
    public Iterable<E> findAll() {
        logger.info("Find all " + entityType.getName());
        return repo.findAll();
    }

    @Override
    public E save(E entity) throws ValidationException {
        if (entity == null)
            throw new IllegalArgumentException("entity is null");
        logger.info("Save " + entityType.getName() + " with id: " + entity.getId());
        validator.validate(entity);

        entity.setId(null);
        return repo.save(entity);
    }

    @Override
    public E update(E entity) throws ValidationException {
        if (entity == null)
            throw new IllegalArgumentException("entity is null");
        logger.info("Update " + entityType.getName() + " with id: " + entity.getId());
        validator.validate(entity);

        if (entity.getId() == null)
            return null;
        return repo.save(entity);
    }

    @Override
    public E delete(ID id) {
        if (id == null)
            throw new IllegalArgumentException("id is null");
        logger.info("Delete " + entityType.getName() + " with id: " + id);
        E entity = this.findByID(id);
        if (entity != null)
            repo.deleteById(id);
        return entity;
    }
}
