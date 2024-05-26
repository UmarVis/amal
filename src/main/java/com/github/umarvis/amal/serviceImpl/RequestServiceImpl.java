package com.github.umarvis.amal.serviceImpl;

import com.github.umarvis.amal.dtos.RequestDto;
import com.github.umarvis.amal.entities.Request;
import com.github.umarvis.amal.entities.User;
import com.github.umarvis.amal.exception.RequestException;
import com.github.umarvis.amal.exception.RequestNotFoundException;
import com.github.umarvis.amal.exception.UserNotFoundException;
import com.github.umarvis.amal.repository.RequestRepository;
import com.github.umarvis.amal.repository.UserRepository;
import com.github.umarvis.amal.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public RequestDto update(Integer userId, RequestDto dto, Integer id) { //todo доб-ть дату обновления
        checkUser(userId);
        Request request = requestRepository.findById(id).orElseThrow(()
                -> new RequestNotFoundException("Запрос на вещь с " + id + " не найдена"));
        if (userId != request.getRequester().getId()) {
            log.warn("Пользователь с ИД [{}] не является владельцем запроса с ИД [{}]", userId, id);
            throw new RequestException("Пользователь с ИД " + userId + " не является владельцем запроса с ИД " + id);
        }
        if (dto.getName() != null && !(dto.getName().isBlank())) {
            log.info("У запроса с ИД [{}] обновлено название [{}]", id, dto.getName());
            request.setName(dto.getName());
        }
        if (dto.getDescription() != null && !(dto.getDescription().isBlank())) {
            log.info("У запроса с ИД [{}] обновлено описание [{}]", id, dto.getDescription());
            request.setDescription(dto.getDescription());
        }
        log.info("Запрос с ИД [{}] обновлена пользователем с ИД [{}]", id, userId);
        return toDto(requestRepository.save(request));
    }

    @Override
    public List<RequestDto> getAll(Integer userId) {
        log.info("Получение всех запросов пользователя с ИД [{}]", userId);
        User user = checkUser(userId);
        return requestRepository.findAllByRequesterOrderByCreated(user).stream()
                .map(this::toDto).collect(Collectors.toList());
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
