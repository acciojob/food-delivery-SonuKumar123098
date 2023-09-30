package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import com.driver.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderService orderService;
	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{
		OrderDto orderDto=orderService.getOrderById(id);
		return OrderTransformer.OrderDtoToOrderDetailsResponse(orderDto);
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		OrderDto orderDto=orderService.createOrder(OrderTransformer.OrderDetailsRequestModelToOrderDto(order));
		return OrderTransformer.OrderDtoToOrderDetailsResponse(orderDto);
	}
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception {
		OrderDto orderDto=orderService.updateOrderDetails(id,OrderTransformer.OrderDetailsRequestModelToOrderDto(order));
		return OrderTransformer.OrderDtoToOrderDetailsResponse(orderDto);
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		orderService.deleteOrder(id);
		OperationStatusModel operationStatusModel=new OperationStatusModel();
		operationStatusModel.setOperationName("delete");
		operationStatusModel.setOperationResult("order deleted succesfully");
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDto>orderDtos=orderService.getOrders();
		List<OrderDetailsResponse>orderDetailsResponses=new ArrayList<>();
		for (OrderDto orderDto:orderDtos){
			OrderDetailsResponse orderDetailsResponse=OrderTransformer.OrderDtoToOrderDetailsResponse(orderDto);
			orderDetailsResponses.add(orderDetailsResponse);
		}
		return orderDetailsResponses;
	}
}
