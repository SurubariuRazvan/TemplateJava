package com.jderu.repository.JPARepository;

import com.jderu.domain.Manager;
import org.springframework.data.repository.CrudRepository;

public interface ManagerJPARepository extends CrudRepository<Manager, Integer> {
}
