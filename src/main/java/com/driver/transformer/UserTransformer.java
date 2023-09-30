package com.driver.transformer;

import com.driver.io.entity.UserEntity;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.UserResponse;
import com.driver.shared.dto.UserDto;

import java.util.UUID;

public class UserTransformer {
    public static UserDto userDetailRequestModelToUserDto(UserDetailsRequestModel userDetailsRequestModel){
        return UserDto.builder()
                .userId(String.valueOf(UUID.randomUUID()))
                .email(userDetailsRequestModel.getEmail())
                .firstName(userDetailsRequestModel.getFirstName())
                .lastName(userDetailsRequestModel.getLastName())
                .build();
    }
    public static UserResponse userDtoToUserResponse(UserDto userDto){
        return UserResponse.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .userId(userDto.getUserId())
                .lastName(userDto.getLastName())
                .build();
    }
    public static UserDto userEntityToUserDto(UserEntity userEntity){
        return UserDto.builder()
                .lastName(userEntity.getLastName())
                .id(userEntity.getId()).firstName(userEntity.getFirstName())
                .email(userEntity.getEmail())
                .userId(userEntity.getUserId())
                .firstName(userEntity.getFirstName())
                .build();
    }
}
