package com.github.umarvis.amal.dtos;

import com.github.umarvis.amal.entities.Request;
import com.github.umarvis.amal.entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {
    private Long id;
    private String name;
    private String description;
    private Boolean inStock;
    private User owner;
    private Request request;
}
