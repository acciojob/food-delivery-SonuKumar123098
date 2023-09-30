package com.driver.transformer;

import com.driver.io.entity.OrderEntity;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.shared.dto.OrderDto;

import java.util.UUID;

public class OrderTransformer {


    public static OrderDto OrderEntityToOrderDto(OrderEntity orderEntity){
        return  OrderDto.builder()
                .items(orderEntity.getItems())
                .id(orderEntity.getId())
                .orderId(orderEntity.getOrderId())
                .userId(orderEntity.getUserId())
                .cost(orderEntity.getCost())
                .status(orderEntity.isStatus())
                .build();
    }
    public static OrderEntity OrderDtoToOrderEntity(OrderDto orderDto){
        return OrderEntity.builder()
                .cost(orderDto.getCost())
                .orderId(String.valueOf(UUID.randomUUID()))
                .items(orderDto.getItems())
                .userId(orderDto.getUserId())
                .status(true)
                .build();
    }
    public static OrderDetailsResponse OrderDtoToOrderDetailsResponse(OrderDto orderDto){
        return OrderDetailsResponse.builder()
                .cost(orderDto.getCost())
                .items(orderDto.getItems())
                .userId(orderDto.getUserId())
                .status(orderDto.isStatus())
                .orderId(orderDto.getOrderId())
                .build();
    }
    public static OrderDto OrderDetailsRequestModelToOrderDto(OrderDetailsRequestModel orderDetailsRequestModel){
        return OrderDto.builder()
                .cost(orderDetailsRequestModel.getCost())
                .userId(orderDetailsRequestModel.getUserId())
                .items(orderDetailsRequestModel.getItems())
                .build();
    }
}

