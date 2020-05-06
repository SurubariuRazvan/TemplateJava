package com.jderu.repository.database;

import com.jderu.domain.Task;
import com.jderu.domain.User;
import com.jderu.domain.UserType;
import com.jderu.repository.JPARepository.TaskJPARepository;
import com.jderu.repository.JPARepository.UserJPARepository;
import com.jderu.repository.TaskRepository;
import com.jderu.repository.UserRepository;
import com.jderu.validation.CRUDValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDatabaseRepository extends AbstractJPARepository<Task, Integer> implements TaskRepository {
    private final TaskJPARepository repo;

    @Autowired
    public TaskDatabaseRepository(@Qualifier("taskValidator") CRUDValidator<Task> validator, TaskJPARepository repo) {
        super(validator, Task.class, repo);
        this.repo = repo;
    }
}
