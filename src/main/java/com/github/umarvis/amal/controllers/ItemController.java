package com.github.umarvis.amal.controllers;

import com.github.umarvis.amal.dtos.ItemDto;
import com.github.umarvis.amal.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/add")
    private ItemDto add(@RequestHeader ("X-Sharer-User-Id") Integer userId, @RequestBody ItemDto dto) {
        return itemService.add(userId, dto);
    }
}
