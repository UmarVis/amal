package com.github.umarvis.amal.controllers;

import com.github.umarvis.amal.dtos.UserDto;
import com.github.umarvis.amal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    public UserDto add(@RequestBody UserDto userDto) {
        return userService.add(userDto);
    }

    @PatchMapping("{id}")
    public UserDto update(@RequestBody UserDto dto, @PathVariable Integer id) {
        return userService.update(dto, id);
    }

    @GetMapping("{id}")
    public UserDto getId(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @GetMapping("/all")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @DeleteMapping({"{id}"})
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }
}
