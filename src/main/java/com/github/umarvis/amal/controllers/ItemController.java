package com.github.umarvis.amal.controllers;

import com.github.umarvis.amal.dtos.ItemDto;
import com.github.umarvis.amal.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/add")
    private ItemDto add(@RequestBody ItemDto dto) {
        return itemService.add(dto);
    }
}
