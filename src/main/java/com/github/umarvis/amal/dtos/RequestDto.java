package com.github.umarvis.amal.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.umarvis.amal.entities.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class RequestDto {
    private Integer id;
    private String name;
    private String description;
    private User requester;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime created;
}
