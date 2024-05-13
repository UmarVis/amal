package com.github.umarvis.amal.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Request {

    private Long id;
    private String description;
    private User requestor;
    private LocalDateTime created;
}
