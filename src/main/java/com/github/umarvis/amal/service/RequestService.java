package com.github.umarvis.amal.service;

import com.github.umarvis.amal.dtos.RequestDto;

import java.util.List;

public interface RequestService {
    RequestDto add(Integer userId, RequestDto dto);

    RequestDto update(Integer userId, RequestDto dto, Integer id);

    List<RequestDto> getAll(Integer userId);
}
