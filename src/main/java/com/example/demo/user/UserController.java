package com.example.demo.user;

import com.example.demo.room.RoomService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService userService) {
        this.service = userService;
    }
}
