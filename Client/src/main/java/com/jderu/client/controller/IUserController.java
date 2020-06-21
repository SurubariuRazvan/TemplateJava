package com.jderu.client.controller;

import com.jderu.domain.User;
import com.jderu.service.IAppObserver;
import com.jderu.service.IAppServices;

public interface IUserController extends IAppObserver {
    void setService(IAppServices appService, User user);

    void logout();
}
