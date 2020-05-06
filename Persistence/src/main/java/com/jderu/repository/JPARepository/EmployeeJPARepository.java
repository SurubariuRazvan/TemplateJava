package com.jderu.repository.JPARepository;

import com.jderu.domain.Employee;
import com.jderu.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeJPARepository extends CrudRepository<Employee, Integer> {
}
