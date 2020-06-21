package com.jderu.repository.database;

import com.jderu.domain.User;
import com.jderu.domain.UserType;
import com.jderu.repository.JPARepository.UserJPARepository;
import com.jderu.repository.UserRepository;
import com.jderu.validation.CRUDValidator;
import com.jderu.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDatabaseRepository extends AbstractJPARepository<User, Integer> implements UserRepository {
    private final UserJPARepository repo;

    @Autowired
    public UserDatabaseRepository(@Qualifier("userValidator") CRUDValidator<User> validator, UserJPARepository repo) {
        super(validator, User.class, repo);
        this.repo = repo;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        logger.info(entityType.getName() + ": find by username: " + username + " and password: " + password);
        return repo.findByUsernameAndPassword(username, User.encodePassword(password));
    }

    @Override
    public List<User> findAllByUserType(UserType userType) {
        logger.info(entityType.getName() + ": find by user type: " + userType);
        return repo.findAllByUserType(userType);
    }
}
