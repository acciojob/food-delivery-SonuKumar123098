package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import com.driver.transformer.FoodTransformer;
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
@RequestMapping("/foods")
public class FoodController {
	@Autowired
	FoodService foodService;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable("id") String id) throws Exception{
		FoodDto foodDto=foodService.getFoodById(id);
		return FoodTransformer.FoodDtoToFoodDetailsResponse(foodDto);
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		FoodDto foodDto=foodService.createFood(FoodTransformer.FoodDetailsRequestModelToFoodDto(foodDetails));
		return FoodTransformer.FoodDtoToFoodDetailsResponse(foodDto);
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable("id") String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDto foodDto=foodService.updateFoodDetails(id,FoodTransformer.FoodDetailsRequestModelToFoodDto(foodDetails));
		return FoodTransformer.FoodDtoToFoodDetailsResponse(foodDto);
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable("id") String id) throws Exception{
		foodService.deleteFoodItem(id);
		OperationStatusModel operationStatusModel=new OperationStatusModel();
		operationStatusModel.setOperationResult("food item deleted successfully");
		operationStatusModel.setOperationName("delete");
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {
		List<FoodDto>foodDtos=foodService.getFoods();
		List<FoodDetailsResponse>foodDetailsResponses=new ArrayList<>();
		for(FoodDto foodDto:foodDtos){
			FoodDetailsResponse foodDetailsResponse=FoodTransformer.FoodDtoToFoodDetailsResponse(foodDto);
			foodDetailsResponses.add(foodDetailsResponse);
		}
		return foodDetailsResponses;
	}
}
