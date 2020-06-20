package com.exam.server;

import com.exam.domain.UserType;
import com.exam.service.IAppObserver;

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
