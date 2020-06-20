package com.exam.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Manager")
public class Manager extends User {
    public Manager() {
    }

    public Manager(User user) {
        super(user);
        super.setUserType(UserType.Manager);
    }

    public Manager(Integer id, String username, String password, String name) {
        super(id, username, password, name, UserType.Manager);
    }
}
