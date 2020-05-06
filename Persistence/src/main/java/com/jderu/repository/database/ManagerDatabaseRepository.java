package com.jderu.repository.database;

import com.jderu.domain.Manager;
import com.jderu.repository.JPARepository.ManagerJPARepository;
import com.jderu.repository.ManagerRepository;
import com.jderu.validation.CRUDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ManagerDatabaseRepository extends AbstractJPARepository<Manager, Integer> implements ManagerRepository {
    private final ManagerJPARepository repo;

    @Autowired
    public ManagerDatabaseRepository(@Qualifier("managerValidator") CRUDValidator<Manager> validator, ManagerJPARepository repo) {
        super(validator, Manager.class, repo);
        this.repo = repo;
    }
}
