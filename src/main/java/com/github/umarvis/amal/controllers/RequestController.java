package com.github.umarvis.amal.controllers;

import com.github.umarvis.amal.dtos.RequestDto;
import com.github.umarvis.amal.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/request")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/add")
    private RequestDto add(@RequestHeader("X-Sharer-User-Id") Integer userId, @RequestBody RequestDto dto) {
        return requestService.add(userId, dto);
    }

    @PatchMapping("{id}")
    private RequestDto update(@RequestHeader("X-Sharer-User-Id") Integer userId,
                              @RequestBody RequestDto dto, @PathVariable Integer id) {
        return requestService.update(userId, dto, id);
    }

    @GetMapping("/all")
    private List<RequestDto> getAll(@RequestHeader("X-Sharer-User-Id") Integer userId) {
        return requestService.getAll(userId);
    }
}
