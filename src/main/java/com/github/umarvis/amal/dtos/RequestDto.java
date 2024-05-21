package com.github.umarvis.amal.dtos;

import com.github.umarvis.amal.entities.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestDto {
    private Long id;
    private String description;
    private User requestor;
    private LocalDateTime created;
}
