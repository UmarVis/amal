package com.github.umarvis.amal.service;

import com.github.umarvis.amal.dtos.ItemDto;

public interface ItemService {
    ItemDto add(Integer userId, ItemDto dto);

    ItemDto update(Integer userId, ItemDto dto, Integer id);

    ItemDto findById(Integer id);
}
