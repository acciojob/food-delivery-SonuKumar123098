package com.driver.io.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.driver.io.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);

	void deleteByUserId(String userId);

	@Query(value = "select * from users",nativeQuery = true)
	List<UserEntity> getAllUserEntity();
}
