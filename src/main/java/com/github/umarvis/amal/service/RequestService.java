package com.github.umarvis.amal.service;

import com.github.umarvis.amal.dtos.RequestDto;

public interface RequestService {
    RequestDto add(Integer userId, RequestDto dto);
}
