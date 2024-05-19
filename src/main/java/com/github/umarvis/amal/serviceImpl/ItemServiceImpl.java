package com.github.umarvis.amal.serviceImpl;

import com.github.umarvis.amal.dtos.ItemDto;
import com.github.umarvis.amal.entities.Item;
import com.github.umarvis.amal.repository.ItemRepository;
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

    @Override
    public ItemDto add(ItemDto dto) {
        Item item = toEntity(dto);
        log.info("Item with ID [{}], name [{}], description [{}], inStock [{}], owner [{}], request created",
                item.getId(), item.getName(), item.getDescription(), item.getInStock(), item.getOwner());
        return toDto(itemRepository.saveAndFlush(item));
    }

    private ItemDto toDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .inStock(item.getInStock())
                .owner(item.getOwner())
                .request(item.getRequest())
                .build();
    }

    private Item toEntity(ItemDto dto) {
        return Item.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .inStock(dto.getInStock())
                .owner(dto.getOwner())
                .request(dto.getRequest())
                .build();
    }
}
