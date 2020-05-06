package com.jderu.repository.database;

import com.jderu.domain.Employee;
import com.jderu.domain.User;
import com.jderu.repository.EmployeeRepository;
import com.jderu.repository.JPARepository.EmployeeJPARepository;
import com.jderu.repository.JPARepository.UserJPARepository;
import com.jderu.repository.UserRepository;
import com.jderu.validation.CRUDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDatabaseRepository extends AbstractJPARepository<Employee, Integer> implements EmployeeRepository {
    private final EmployeeJPARepository repo;

    @Autowired
    public EmployeeDatabaseRepository(@Qualifier("employeeValidator") CRUDValidator<Employee> validator, EmployeeJPARepository repo) {
        super(validator, Employee.class, repo);
        this.repo = repo;
    }
}
