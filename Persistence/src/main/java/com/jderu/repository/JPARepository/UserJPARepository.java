package com.jderu.repository.JPARepository;

import com.jderu.domain.User;
import com.jderu.domain.UserType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserJPARepository extends CrudRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);

    List<User> findAllByUserType(UserType userType);
}
