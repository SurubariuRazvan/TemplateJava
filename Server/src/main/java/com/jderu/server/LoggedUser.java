package com.jderu.server;

import com.jderu.domain.UserType;
import com.jderu.service.IAppObserver;

public class LoggedUser {
    private UserType userType;
    private IAppObserver observer;

    public LoggedUser(UserType userType, IAppObserver observer) {
        this.userType = userType;
        this.observer = observer;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public IAppObserver getObserver() {
        return observer;
    }

    public void setObserver(IAppObserver observer) {
        this.observer = observer;
    }
}
