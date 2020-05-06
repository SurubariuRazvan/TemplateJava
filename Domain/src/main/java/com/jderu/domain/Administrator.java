package com.jderu.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Administrator")
public class Administrator extends User {
    private String administratorAttribute;

    public Administrator() {
    }

    public Administrator(User user, String administratorAttribute) {
        super(user);
        super.setUserType(UserType.Administrator);
        this.administratorAttribute = administratorAttribute;
    }

    public Administrator(Integer id, String username, String password, String name, String administratorAttribute) {
        super(id, username, password, name, UserType.Administrator);
        this.administratorAttribute = administratorAttribute;
    }

    public String getAdministratorAttribute() {
        return administratorAttribute;
    }

    public void setAdministratorAttribute(String administratorAttribute) {
        this.administratorAttribute = administratorAttribute;
    }
}
