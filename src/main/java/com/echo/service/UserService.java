package com.echo.service;

import com.echo.po.User;

public interface UserService {

    User checkUser(String username, String password);
}
