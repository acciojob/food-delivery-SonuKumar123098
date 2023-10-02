package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import com.driver.transformer.FoodTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    FoodRepository foodRepository;
    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity= FoodTransformer.FoodDtoToFoodEntity(food);
        FoodEntity savedFoodEntity=foodRepository.save(foodEntity);
        FoodDto foodDto=FoodTransformer.FoodEntityToFoodDto(savedFoodEntity);
        return foodDto;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity foodEntity=foodRepository.findByFoodId(foodId);
        if(foodEntity==null) {
            throw new Exception("food not found");
        }
        return FoodTransformer.FoodEntityToFoodDto(foodEntity);
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        FoodEntity foodEntity=foodRepository.findByFoodId(foodId);
        if(foodEntity==null) {
            throw new Exception("food not found");
        }
        foodEntity.setFoodCategory(foodDetails.getFoodCategory());
        foodEntity.setFoodName(foodDetails.getFoodName());
        foodEntity.setFoodPrice(foodDetails.getFoodPrice());
        FoodEntity savedFoodEntity=foodRepository.save(foodEntity);
        return FoodTransformer.FoodEntityToFoodDto(savedFoodEntity);
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        FoodEntity foodEntity=foodRepository.findByFoodId(id);
        if(foodEntity==null) {
            throw new Exception("food not found");
        }
        foodRepository.deleteById(foodEntity.getId());
    }

    @Override
    public List<FoodDto> getFoods() {
        List<FoodEntity>foodEntities= (List<FoodEntity>) foodRepository.findAll();
        List<FoodDto>foodDtos=new ArrayList<>();
        for(FoodEntity foodEntity:foodEntities){
            FoodDto foodDto=FoodTransformer.FoodEntityToFoodDto(foodEntity);
            foodDtos.add(foodDto);
        }
        return foodDtos;
    }
}