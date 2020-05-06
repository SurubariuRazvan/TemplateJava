package com.jderu.repository.JPARepository;

import com.jderu.domain.Administrator;
import org.springframework.data.repository.CrudRepository;

public interface AdministratorJPARepository extends CrudRepository<Administrator, Integer> {
}
