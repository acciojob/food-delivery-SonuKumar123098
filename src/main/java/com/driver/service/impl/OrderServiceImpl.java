package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.entity.OrderEntity;
import com.driver.io.entity.UserEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.io.repository.OrderRepository;
import com.driver.io.repository.UserRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import com.driver.transformer.FoodTransformer;
import com.driver.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FoodRepository foodRepository;
    @Override
    public OrderDto createOrder(OrderDto order) {
        UserEntity userEntity =userRepository.findByUserId(order.getUserId());
        OrderEntity orderEntity= OrderTransformer.OrderDtoToOrderEntity(order);
        orderEntity.setUserEntity(userEntity);
        for(String item:order.getItems()){
            orderEntity.getFoodEntities().add(foodRepository.findByFoodId(item));
        }
        OrderEntity orderEntity1=orderRepository.save(orderEntity);
        return OrderTransformer.OrderEntityToOrderDto(orderEntity1);
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
       OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
       if(orderEntity==null){
           throw new Exception("order not found");
       }
       return OrderTransformer.OrderEntityToOrderDto(orderEntity);
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        if(orderEntity==null){
            throw new Exception(("order not found"));
        }
        UserEntity userEntity=userRepository.findByUserId(order.getUserId());
        if(userEntity==null){
            throw new Exception("user not found");
        }
        orderEntity.setCost(order.getCost());
        orderEntity.setUserId(order.getUserId());
        orderEntity.setItems(order.getItems());
        orderEntity.setUserEntity(userEntity);
        for(String foodId:order.getItems()){
            FoodEntity foodEntity=foodRepository.findByFoodId(foodId);
            if(foodEntity==null){
                throw new Exception("food item not found");
            }
            orderEntity.getFoodEntities().add(foodEntity);
        }
        OrderEntity saved=orderRepository.save(orderEntity);
        return OrderTransformer.OrderEntityToOrderDto(saved);

    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        if(orderEntity==null){
            throw new Exception("order not found");
        }
        orderRepository.deleteById(orderEntity.getId());
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderEntity>orderEntities=orderRepository.getAllOrders();
        List<OrderDto>orderDtos=new ArrayList<>();
        for(OrderEntity orderEntity:orderEntities){
            OrderDto orderDto=OrderTransformer.OrderEntityToOrderDto(orderEntity);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }
}