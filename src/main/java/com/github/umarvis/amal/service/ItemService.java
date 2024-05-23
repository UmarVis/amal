package com.github.umarvis.amal.service;

import com.github.umarvis.amal.dtos.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto add(Integer userId, ItemDto dto);

    ItemDto update(Integer userId, ItemDto dto, Integer id);

    ItemDto findById(Integer id);

    List<ItemDto> getAll(Integer userId);

    List<ItemDto> search(String text);
}
