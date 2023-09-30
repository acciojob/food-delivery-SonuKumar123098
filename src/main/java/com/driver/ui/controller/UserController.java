package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.driver.io.entity.UserEntity;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.UserResponse;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import com.driver.transformer.UserTransformer;
import org.modelmapper.internal.bytebuddy.dynamic.DynamicType;
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
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable("id") String id) throws Exception{
		UserDto userDto=userService.getUserByUserId(id);
		UserResponse userResponse=UserTransformer.UserDtoToUserResponse(userDto);
		return userResponse;
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto=userService.createUser(UserTransformer.UserDetailRequestModelToUserDto(userDetails));
		return UserTransformer.UserDtoToUserResponse(userDto);
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable("id") String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto=userService.updateUser(id,UserTransformer.UserDetailRequestModelToUserDto(userDetails));
		UserResponse userResponse=UserTransformer.UserDtoToUserResponse(userDto);
		return userResponse;

	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable("id") String id) throws Exception{
		userService.deleteUser(id);
		OperationStatusModel operationStatusModel=new OperationStatusModel();
		operationStatusModel.setOperationName("delete");
		operationStatusModel.setOperationResult("deleted successfully");
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<UserResponse> getUsers(){
		List<UserDto> userDtoList=userService.getUsers();
		List<UserResponse>userResponseList=new ArrayList<>();
		for(UserDto userDto:userDtoList){
			UserResponse userResponse=UserTransformer.UserDtoToUserResponse(userDto);
			userResponseList.add(userResponse);
		}
		return userResponseList;
	}
	
}
