package com.exam.client.controller;

import com.exam.domain.User;
import com.exam.service.IAppObserver;
import com.exam.service.IAppServices;

public interface IUserController extends IAppObserver {
    void setService(IAppServices appService, User user);

    void logout();
}
