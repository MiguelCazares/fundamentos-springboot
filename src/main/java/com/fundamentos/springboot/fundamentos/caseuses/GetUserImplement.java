package com.fundamentos.springboot.fundamentos.caseuses;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser {
    private final UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
