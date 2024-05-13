package com.github.umarvis.amal.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {

    private Long id;
    private String name;
    private String description;
    private Boolean inStock;
    private Integer owner;
    private Request request;

}
