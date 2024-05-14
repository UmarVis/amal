package com.github.umarvis.amal.controllers;

import com.github.umarvis.amal.dtos.UserDto;
import com.github.umarvis.amal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    public UserDto add(@RequestBody UserDto userDto) {
        return userService.add(userDto);
    }
}
