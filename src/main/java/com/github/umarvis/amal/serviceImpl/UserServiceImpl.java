package com.github.umarvis.amal.serviceImpl;

import com.github.umarvis.amal.dtos.UserDto;
import com.github.umarvis.amal.entities.User;
import com.github.umarvis.amal.exception.UserNotFoundException;
import com.github.umarvis.amal.repository.UserRepository;
import com.github.umarvis.amal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    @Transactional
    public UserDto update(UserDto dto, Integer id) {
        //todo check number and email
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("Пользователь с ИД " + id + " не найден"));
        if (dto.getName() != null && !(dto.getName().isBlank())) {
            user.setName(dto.getName());
        }
        if (dto.getEmail() != null && !(dto.getEmail().isBlank())) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getNumberPhone() != null && !(dto.getNumberPhone().isBlank())) {
            user.setNumberPhone(dto.getNumberPhone());
        }
        log.info("Полтьователь с ИД {} обновлен", id);
        userRepository.save(user);
        return toDto(user);
    }

    @Override
    public UserDto getById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("Пользователь с ИД " + id + " не найден"));
        return toDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("Пользователь с ИД " + id + " не найден"));
        log.info("Пользователь с ИД {} удален", id);
        userRepository.deleteById(id);
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
