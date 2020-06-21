package com.exam.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Administrator")
public class Administrator extends User {

    public Administrator() {
    }

    public Administrator(User user) {
        super(user);
        super.setUserType(UserType.Administrator);
    }

    public Administrator(Integer id, String username, String password, String name) {
        super(id, username, password, name, UserType.Administrator);
    }
}
