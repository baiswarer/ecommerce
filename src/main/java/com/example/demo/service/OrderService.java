package com.example.demo.service;

import com.example.demo.entity.Items;
import com.example.demo.entity.Orders;
import com.example.demo.payload.OrderDto;
import com.example.demo.repository.ItemsRepository;
import com.example.demo.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrdersRepository ordersRepository;
    private ItemsRepository itemsRepository;

    public OrderService(OrdersRepository ordersRepository, ItemsRepository itemsRepository) {
        this.ordersRepository = ordersRepository;
        this.itemsRepository = itemsRepository;
    }

    public Orders createNewOrder(OrderDto orderDto) {
        Optional<Items> byId = itemsRepository.findById(orderDto.getItemId());
        if(byId.isPresent()){
            Orders orders =new Orders();
            orders.setItems(byId.get());
            orders.setOrderQuantity(orderDto.getOrderQuantity());
            orders.setOrderAmount(orderDto.getOrderQuantity()*byId.get().getItemPrice());
            orders.setOrderDate(orderDto.getOrderDate());
            Orders save = ordersRepository.save(orders);
            return save;

        }

        return null;
    }

    public Orders updateOrder(Long id, OrderDto orderDto) {
        Optional<Orders> byId = ordersRepository.findById(id);
        if(byId.isPresent()){
            Optional<Items> item = itemsRepository.findById(orderDto.getItemId());
            Orders orders = byId.get();
            orders.setItems(item.get());
            orders.setOrderDate(orderDto.getOrderDate());
            orders.setOrderAmount(orderDto.getOrderQuantity()*item.get().getItemPrice());
            orders.setOrderQuantity(orderDto.getOrderQuantity());
            Orders save = ordersRepository.save(orders);
            return save;

        }
        return null;
    }

    @Transactional
    public boolean removeOrder(Long id) {
        ordersRepository.deleteById(id);
        Optional<Orders> byId = ordersRepository.findById(id);
        if(byId.isEmpty()){
            return true;
        }
        return false;
    }

    public Orders getOrder(Long id) {
        Optional<Orders> byId = ordersRepository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;
    }

    public List<Orders> getAllOrders() {
        List<Orders> all = ordersRepository.findAll();
        return all;
    }
}
