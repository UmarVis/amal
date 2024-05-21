package com.github.umarvis.amal.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String numberPhone;
}
