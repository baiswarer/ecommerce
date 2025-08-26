package com.example.demo.controller;

import com.example.demo.entity.Items;
import com.example.demo.payload.ItemDto;
import com.example.demo.service.ItemsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemsController {


    private ItemsService itemsService;


    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createItem(@RequestBody ItemDto  itemDto){
        itemsService.createNewItem(itemDto);
        return new ResponseEntity<>("Item Added Successfully...", HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Long id,@RequestBody ItemDto dto){
        Items items = itemsService.updateItem(id, dto);
        if(items==null){
            return new ResponseEntity<>("No Item found with id : " +id,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Item Details  Upadted ..",HttpStatus.CREATED);
    }

    @GetMapping("/get-item-by-id")
    public ResponseEntity<?> getItemById(@RequestParam Long id){
        Items item = itemsService.getItem(id);
        if(item!=null){
            return new ResponseEntity<>(item,HttpStatus.OK);
        }
        return new ResponseEntity<>("Item Not Found with Id " +id,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/getAllItems")
    public ResponseEntity<?> getAllItems(){
        List<Items> all = itemsService.getAll();

        return new ResponseEntity<>(all,HttpStatus.OK);
    }


    @DeleteMapping("/removeItem")
    public ResponseEntity<String> RemoveItemById(@Valid @RequestParam Long id, BindingResult result) {
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        boolean status = itemsService.deleteItem(id);
        if(status){
            return new ResponseEntity<>("Item Removed Successfully ...",HttpStatus.OK);
        }
        return new ResponseEntity<>("Error while removing...",HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
