package com.example.demo.controller;

import com.example.demo.entity.Orders;
import com.example.demo.payload.OrderDto;
import com.example.demo.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> CreateOrder(@Valid  @RequestBody OrderDto orderDto, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        Orders newOrder = orderService.createNewOrder(orderDto);
        if(newOrder!=null){
            return new ResponseEntity<>("Order Placed ....", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Error while placing order ...",HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Long id,@RequestBody OrderDto orderDto){
        Orders orders = orderService.updateOrder(id, orderDto);
        if(orders!=null){
            return  new ResponseEntity<>("Order details Upadted ...",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Order not updated ...",HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @DeleteMapping("/cancelOrder")
    public ResponseEntity<String> CancelOrder(@RequestParam Long id){
        boolean status = orderService.removeOrder(id);
        if(status){
            return new ResponseEntity<>("Order cancelled ....",HttpStatus.OK);
        }
        return new ResponseEntity<>("Order can not be cancel ...",HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/getOrderById")
    public ResponseEntity<?> getOrderById(@RequestParam Long id){
        Orders order = orderService.getOrder(id);
        if(order!=null){
            return new ResponseEntity<>(order,HttpStatus.OK);
        }
        return new ResponseEntity<>("No order found for id :" + id,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/GetAllOrders")
    public  ResponseEntity<?> getAll(){
        List<Orders> allOrders = orderService.getAllOrders();
        return new ResponseEntity<>(allOrders,HttpStatus.OK);

    }
}
