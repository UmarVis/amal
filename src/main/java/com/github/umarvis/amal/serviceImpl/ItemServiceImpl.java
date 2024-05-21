package com.github.umarvis.amal.serviceImpl;

import com.github.umarvis.amal.dtos.ItemDto;
import com.github.umarvis.amal.entities.Item;
import com.github.umarvis.amal.entities.User;
import com.github.umarvis.amal.exception.ItemException;
import com.github.umarvis.amal.exception.ItemNotFoundException;
import com.github.umarvis.amal.exception.UserNotFoundException;
import com.github.umarvis.amal.repository.ItemRepository;
import com.github.umarvis.amal.repository.UserRepository;
import com.github.umarvis.amal.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ItemDto add(Integer userId, ItemDto dto) {
        User user = checkUser(userId);
        Item item = toEntity(dto);
        item.setOwner(user);
        log.info("Item with ID [{}], name [{}], description [{}], inStock [{}], owner [{}], request created",
                item.getId(), item.getName(), item.getDescription(), item.getInStock(), item.getOwner());
        return toDto(itemRepository.save(item));
    }

    @Override
    @Transactional
    public ItemDto update(Integer userId, ItemDto dto, Integer id) {
        checkUser(userId);
        Item item = itemRepository.findById(id).orElseThrow(()
                -> new ItemNotFoundException("Вещь с ИД " + id + " не найдена"));
        if (userId != item.getOwner().getId()) {
            log.warn("Пользователь с ИД [{}] не является владельцем вещи с ИД [{}]", userId, id);
            throw new ItemException("Пользователь с ИД " + userId + " не является владельцем вещи с ИД " + id);
        }
        if (dto.getName() != null && !(dto.getName().isBlank())) {
            log.info("У вещи с ИД [{}] обновлено название [{}]", id, dto.getName());
            item.setName(dto.getName());
        }
        if (dto.getDescription() != null && !(dto.getDescription().isBlank())) {
            log.info("У вещи с ИД [{}] обновлено описание [{}]", id, dto.getDescription());
            item.setDescription(dto.getDescription());
        }
        if (dto.getInStock() != null) {
            log.info("У вещи с ИД [{}] обновлена доступность [{}]", id, dto.getInStock());
            item.setInStock(dto.getInStock());
        }
        log.info("Вещь с ИД [{}] обновлена пользователем с ИД [{}]", id, userId);
        return toDto(itemRepository.save(item));
    }

    @Override
    public ItemDto findById(Integer id) {
        log.info("Получена вещь с ИД [{}]", id);
        return toDto(itemRepository.findById(id).orElseThrow(()
                -> new ItemNotFoundException("Вещь с ИД " + id + " не найдена")));
    }

    private User checkUser(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("Пользователь с ИД " + userId + " не найден"));
    }

    private ItemDto toDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .inStock(item.getInStock())
                .owner(item.getOwner())
                .build();
    }

    private Item toEntity(ItemDto dto) {
        return Item.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .inStock(dto.getInStock())
                .owner(dto.getOwner())
                .build();
    }
}
