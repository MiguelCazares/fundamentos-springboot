package com.fundamentos.springboot.fundamentos.caseuses;

import com.fundamentos.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DeleteUser {
    private final UserService userService;

    public DeleteUser(UserService userService) {
        this.userService = userService;
    }

    public void remove(Long id) {
        userService.delete(id);
    }
}
