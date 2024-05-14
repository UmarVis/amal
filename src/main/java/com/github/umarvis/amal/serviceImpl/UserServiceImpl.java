package com.github.umarvis.amal.serviceImpl;

import com.github.umarvis.amal.dtos.UserDto;
import com.github.umarvis.amal.entities.User;
import com.github.umarvis.amal.repository.UserRepository;
import com.github.umarvis.amal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDto add(UserDto userDto) {
        User user = toEntity(userDto);
        log.info("User with ID [{}], name [{}], email [{}], phone [{}] created",
                user.getId(), user.getName(), user.getEmail(), user.getNumberPhone());
        return toDto(userRepository.saveAndFlush(user));
    }

    private User toEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .numberPhone(userDto.getNumberPhone())
                .build();
    }

    private UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .numberPhone(user.getNumberPhone())
                .build();
    }
}
