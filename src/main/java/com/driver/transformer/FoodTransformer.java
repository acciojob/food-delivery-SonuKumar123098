package com.driver.transformer;

import com.driver.io.entity.FoodEntity;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.shared.dto.FoodDto;

import java.util.UUID;

public class FoodTransformer {
    public static FoodDto FoodDetailsRequestModelToFoodDto(FoodDetailsRequestModel foodDetailsRequestModel){
        return FoodDto.builder()
                .foodCategory(foodDetailsRequestModel.getFoodCategory())
                .foodPrice(foodDetailsRequestModel.getFoodPrice())
                .foodName(foodDetailsRequestModel.getFoodName())
                .build();
    }

    public static FoodDto FoodEntityToFoodDto(FoodEntity foodEntity){
        return FoodDto.builder()
                .foodId(foodEntity.getFoodId())
                .foodName(foodEntity.getFoodName())
                .foodPrice(foodEntity.getFoodPrice())
                .foodCategory(foodEntity.getFoodCategory())
                .id(foodEntity.getId())
                .build();
    }

    public static FoodDetailsResponse FoodDtoToFoodDetailsResponse(FoodDto foodDto){
        return FoodDetailsResponse.builder()
                .foodCategory(foodDto.getFoodCategory())
                .foodId(foodDto.getFoodId())
                .foodName(foodDto.getFoodName())
                .foodPrice(foodDto.getFoodPrice())
                .build();
    }

    public static FoodEntity FoodDtoToFoodEntity(FoodDto foodDto){
        return FoodEntity.builder()
                .foodCategory(foodDto.getFoodCategory())
                .foodId(String.valueOf(UUID.randomUUID()))
                .foodName(foodDto.getFoodName())
                .foodPrice(foodDto.getFoodPrice())
                .build();
    }

}
