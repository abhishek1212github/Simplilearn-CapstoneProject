package com.cart.bean;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.cart.dto.CartDto;
import com.cart.dto.FoodItemDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

public class Cart {
	@Id
	private Integer cid;
	private Integer quantity;
	private Double price;
	private Integer id;
	
	
	
	public CartDto convertEntityToDto()
	{
		RestTemplate restTemplate= new RestTemplate();
		FoodItemDto foodItemDto[]=restTemplate.getForObject("http://localhost:8082/foodbox/getAllFoods",FoodItemDto[].class);
		List<FoodItemDto> foodItemDtos=Arrays.asList(foodItemDto); 
		FoodItemDto foodItem=null;
		for(FoodItemDto food:foodItemDtos)
			if(this.getId()==food.getId())
				foodItem=food;
		return new CartDto(this.getCid(),this.getQuantity(),this.getPrice(),foodItem);
	}



	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Cart(Integer cid, Integer quantity, Double price, Integer id) {
		super();
		this.cid = cid;
		this.quantity = quantity;
		this.price = price;
		this.id = id;
	}



	public Integer getCid() {
		return cid;
	}



	public void setCid(Integer cid) {
		this.cid = cid;
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



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
