package com.driver.service.impl;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import com.driver.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto user) throws Exception {
        UserEntity userEntity= UserEntity.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .userId(String.valueOf(UUID.randomUUID()))
                .build();
     UserEntity savedUserEntity=   userRepository.save(userEntity);
        return UserTransformer.UserEntityToUserDto(savedUserEntity);
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity=userRepository.findByEmail(email);
        if(userEntity==null){
            throw new Exception("user not found");
        }
        UserDto userDto= UserTransformer.UserEntityToUserDto(userEntity);
        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity=userRepository.findByUserId(userId);
        if(userEntity==null){
            throw new Exception("user not found");
        }
        UserDto userDto= UserTransformer.UserEntityToUserDto(userEntity);
        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        UserEntity userEntity=userRepository.findByUserId(userId);
        if(userEntity==null){
            throw new Exception("user not found");
        }
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        UserEntity userEntity1=userRepository.save(userEntity);
        UserDto userDto=UserTransformer.UserEntityToUserDto(userEntity1);
        return userDto;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        UserEntity userEntity=userRepository.findByUserId(userId);
        if(userEntity==null)
            throw new Exception("user not found");
        userRepository.deleteById(userEntity.getId());
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity>userEntityList= (List<UserEntity>) userRepository.findAll();
        List<UserDto>userDtoList=new ArrayList<>();
        for(UserEntity userEntity:userEntityList){
            UserDto userDto=UserTransformer.UserEntityToUserDto(userEntity);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}