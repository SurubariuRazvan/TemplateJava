package com.jderu.repository.JPARepository;

import com.jderu.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskJPARepository extends CrudRepository<Task, Integer> {

}
