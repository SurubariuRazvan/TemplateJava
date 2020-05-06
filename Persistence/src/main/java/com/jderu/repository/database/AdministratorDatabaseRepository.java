package com.jderu.repository.database;

import com.jderu.domain.Administrator;
import com.jderu.repository.JPARepository.AdministratorJPARepository;
import com.jderu.repository.AdministratorRepository;
import com.jderu.validation.CRUDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AdministratorDatabaseRepository extends AbstractJPARepository<Administrator, Integer> implements AdministratorRepository {
    private final AdministratorJPARepository repo;

    @Autowired
    public AdministratorDatabaseRepository(@Qualifier("administratorValidator") CRUDValidator<Administrator> validator, AdministratorJPARepository repo) {
        super(validator, Administrator.class, repo);
        this.repo = repo;
    }
}
