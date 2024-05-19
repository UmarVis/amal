package com.github.umarvis.amal.service;

import com.github.umarvis.amal.dtos.UserDto;

import java.util.List;

public interface UserService {
    UserDto add(UserDto userDto);

    UserDto update(UserDto dto, Integer id);

    UserDto getById(Integer id);

    List<UserDto> getAll();

    void delete(Integer id);
}
