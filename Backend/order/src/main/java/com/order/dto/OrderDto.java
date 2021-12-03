package com.order.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.order.bean.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class OrderDto {

	private Integer orderId;
	private Integer userId;
	private Double totalAmount;
	private List<FoodItemDto> foods;
	public Order convertFromDtoToEntity() {
		return new Order(this.getOrderId(),this.getUserId(),this.getTotalAmount());
	}
	public OrderDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDto(Integer orderId, Integer userId, Double totalAmount, List<FoodItemDto> foods) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.foods = foods;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<FoodItemDto> getFoods() {
		return foods;
	}
	public void setFoods(List<FoodItemDto> foods) {
		this.foods = foods;
	}
	
	
}


