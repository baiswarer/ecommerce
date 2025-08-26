package com.example.demo.service;

import com.example.demo.entity.Items;
import com.example.demo.payload.ItemDto;
import com.example.demo.repository.ItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemsService {

 private ItemsRepository itemsRepository;

    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public void createNewItem(ItemDto itemDto) {
        Items items=new Items();
        items.setItemName(itemDto.getItemName());
        items.setItemCategory(itemDto.getItemCategory());
        items.setItemPrice(itemDto.getItemPrice());
        itemsRepository.save(items);
    }

    public Items updateItem(Long id, ItemDto dto) {
        Optional<Items> byId = itemsRepository.findById(id);
        if(byId.isPresent()){
            Items items = byId.get();
            items.setItemName(dto.getItemName());
            items.setItemPrice(dto.getItemPrice());
            items.setItemCategory(dto.getItemCategory());
            Items save = itemsRepository.save(items);
            return save;
        }
        return null;


    }

    public Items getItem(Long id) {
        Optional<Items> itemById = itemsRepository.findById(id);
        if(itemById.isPresent()){
            return itemById.get();
        }
        return null;
    }

    public List<Items> getAll() {
        List<Items> all = itemsRepository.findAll();
        return all;

    }

    public boolean deleteItem(Long id) {
        itemsRepository.deleteById(id);
        Optional<Items> byId = itemsRepository.findById(id);
        if(byId.isEmpty()){
            return true;
        }

        return false;
    }
}
