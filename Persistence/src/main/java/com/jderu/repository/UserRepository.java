package com.jderu.repository;

import com.jderu.domain.User;
import com.jderu.domain.UserType;

import java.util.List;


public interface UserRepository extends CRUDRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);

    List<User> findAllByUserType(UserType userType);
}
