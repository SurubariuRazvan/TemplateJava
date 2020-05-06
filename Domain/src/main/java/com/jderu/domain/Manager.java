package com.jderu.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Manager")
public class Manager extends User {
    private String managerAttribute;

    public Manager() {
    }

    public Manager(User user, String managerAttribute) {
        super(user);
        super.setUserType(UserType.Manager);
        this.managerAttribute = managerAttribute;
    }

    public Manager(Integer id, String username, String password, String name, String managerAttribute) {
        super(id, username, password, name, UserType.Manager);
        this.managerAttribute = managerAttribute;
    }

    public String getManagerAttribute() {
        return managerAttribute;
    }

    public void setManagerAttribute(String managerAttribute) {
        this.managerAttribute = managerAttribute;
    }
}
