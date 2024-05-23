package com.github.umarvis.amal.serviceImpl;

import com.github.umarvis.amal.dtos.RequestDto;
import com.github.umarvis.amal.entities.Request;
import com.github.umarvis.amal.entities.User;
import com.github.umarvis.amal.exception.UserNotFoundException;
import com.github.umarvis.amal.repository.RequestRepository;
import com.github.umarvis.amal.repository.UserRepository;
import com.github.umarvis.amal.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    @Override
    public RequestDto add(Integer userId, RequestDto dto) {
        User user = checkUser(userId);
        Request request = toEntity(userId, dto);
        request.setRequester(user);
        log.info("Запрос на вещь с ИД [{}], с именем [{}], с описанием [{}], пользователем [{}] и с датой [{}] создан",
                request.getId(), request.getName(), dto.getDescription(), request.getRequester(), request.getCreated());
        return toDto(requestRepository.save(request));
    }

    private User checkUser(Integer userId) {
        return userRepository.findById(userId).orElseThrow(()
                -> new UserNotFoundException("Пользователь с ИД " + userId + " не найден"));
    }

    private RequestDto toDto(Request save) {
        return RequestDto.builder()
                .id(save.getId())
                .name(save.getName())
                .description(save.getDescription())
                .requester(save.getRequester())
                .created(save.getCreated())
                .build();
    }

    private Request toEntity(Integer userId, RequestDto dto) {
        User user = checkUser(userId);
        return Request.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .requester(user)
                .created(LocalDateTime.now()).build();
    }
}
