package com.driver.io.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;

@Entity(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String orderId;

	@Column(nullable = false)
	private float cost;

	@Column(nullable = false)
	private String[] items;

	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private boolean status;
	@ManyToOne
	@JoinColumn
	UserEntity userEntity;
	@OneToMany(mappedBy = "orderEntity",cascade = CascadeType.ALL)
	List<FoodEntity>foodEntities=new ArrayList<>();

	public long getId() {
		return id;
	}
	public void addFoodEntities(FoodEntity foodEntity){
		if(foodEntities==null){
			foodEntities=new ArrayList<>();
		}
		this.foodEntities.add(foodEntity);
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
