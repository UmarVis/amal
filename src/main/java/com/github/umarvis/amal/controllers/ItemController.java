package com.github.umarvis.amal.controllers;

import com.github.umarvis.amal.dtos.ItemDto;
import com.github.umarvis.amal.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/add")
    private ItemDto add(@RequestHeader("X-Sharer-User-Id") Integer userId, @RequestBody ItemDto dto) {
        return itemService.add(userId, dto);
    }

    @PatchMapping("{itemId}")
    public ItemDto update(@RequestHeader("X-Sharer-User-Id") Integer userId, @RequestBody ItemDto dto, @PathVariable Integer itemId) {
        return itemService.update(userId, dto, itemId);
    }

    @GetMapping("{id}")
    public ItemDto findById(@PathVariable Integer id) {
        return itemService.findById(id);
    }

    @GetMapping("/all")
    public List<ItemDto> getAll(@RequestHeader ("X-Sharer-User-Id") Integer userId) {
        return itemService.getAll(userId);
    }

    @GetMapping("/search")
    public List<ItemDto> search(@RequestParam String text) {
        return itemService.search(text);
    }
}
