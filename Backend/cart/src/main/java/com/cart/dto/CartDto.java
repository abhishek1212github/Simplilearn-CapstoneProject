package com.cart.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

public class CartDto {

	private Integer id;
	private Integer quantity;
	private Double price;
	private FoodItemDto foodItem;
	public CartDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartDto(Integer id, Integer quantity, Double price, FoodItemDto foodItem) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.foodItem = foodItem;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public FoodItemDto getFoodItem() {
		return foodItem;
	}
	public void setFoodItem(FoodItemDto foodItem) {
		this.foodItem = foodItem;
	}
	
}
