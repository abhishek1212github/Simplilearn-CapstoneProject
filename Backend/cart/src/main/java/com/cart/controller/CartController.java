package com.cart.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cart.bean.Cart;
import com.cart.dao.CartDao;
import com.cart.dto.CartDto;
import com.cart.dto.FoodItemDto;
import com.cart.service.CartService;

@RestController
@CrossOrigin
public class CartController {

	
	@Autowired
	CartService service;
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/carts")
	public List<CartDto> getProductList()
	{
		List<CartDto> cartDto=new ArrayList<CartDto>();
		/*FoodItemDto foodItemDto[]=restTemplate.getForObject("http://localhost:8082/foodbox/getAllFoods",FoodItemDto[].class);
		List<FoodItemDto> foodItemDtos=Arrays.asList(foodItemDto);
		*/
		List<Cart> cart=service.findAll();
		for(Cart c:cart)
			cartDto.add(c.convertEntityToDto());
		return cartDto;
	}
	
	@DeleteMapping("/carts/{id}")
	public CartDto deleteProduct(@PathVariable("id") Integer id)
	{
		return service.delete(id);
	}
	
	@GetMapping(value="/carts/{id}")
	public CartDto addToCart(@PathVariable("id") Integer id, HttpSession session) {
		
		//All the food Items that are available in the database
		FoodItemDto foodItemDto[]=restTemplate.getForObject("http://localhost:8082/foodbox/getAllFoods",FoodItemDto[].class);
		List<FoodItemDto> foodItemDtos=Arrays.asList(foodItemDto); 
		CartDto cart= new CartDto();
		//Bill you have to you order successfully
		double grandTotal=0;
		if(session.getAttribute("grandTotal")==null) {
			grandTotal=0;
		}
		else {
			grandTotal=(float) session.getAttribute("grandTotal");
		}
		
		
		//Retrieving fooditem added in the cart
		FoodItemDto foodItem=null;
		for(FoodItemDto food:foodItemDtos)
			if(id==food.getId())
				foodItem=food;
		
		//getting all the thing available in the cart
		List<Cart> cartList = service.findAll();
		
		
		for(Cart temp:cartList) {
			if(temp.getId()==id) {
				temp.setQuantity(1+temp.getQuantity());
				temp.setPrice(temp.getPrice()*temp.getQuantity());
				grandTotal=grandTotal+temp.getPrice();
				session.setAttribute("grandTotal", grandTotal);
				service.updatePriceAndQuantity(temp);
				cart.setPrice(temp.getPrice());
				cart.setQuantity(temp.getQuantity());
				cart.setId(id);
				cart.setFoodItem(foodItem);
				return cart;
			}
		}
		int b=(int)(Math.random()*(500-100+1)+100);
		Cart cart1=new Cart();
		cart.setPrice(foodItem.getPrice());
		cart1.setPrice(foodItem.getPrice());
		cart.setQuantity(1);
		cart1.setQuantity(1);
		cart.setId(id);
		cart1.setId(id);
		cart1.setCid(b);
		cart.setFoodItem(foodItem);
		grandTotal=grandTotal+foodItem.getPrice();
		session.setAttribute("grandTotal", grandTotal);
		service.save(cart1);
		System.err.println(cart);
		return cart;
	}
/*
	@GetMapping("/carts")
	public List<Cart> getCartItems() {
		return dao.findAll();
	}
	
	@PutMapping("/carts/add/{id}")
	public ResponseEntity<Cart> addByOne(@PathVariable("id") long id,@RequestBody Cart cart){
		--Cart cart = dao.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Product not found with"+id));--
		int quantity= cart.getQuantity()+1;
		cart.setQuantity(quantity);
		cart.setPrice((cart.getProduct().getPrice())*quantity);
		Cart updatedCart = dao.save(cart);
		return ResponseEntity.ok(updatedCart);
	}
	
	@PutMapping("/carts/minus/{id}")
	public ResponseEntity<Cart> lessByOne(@PathVariable("id") long id,@RequestBody Cart cart){
	
		int quantity= cart.getQuantity()-1;
		if(quantity!=0) {
			cart.setQuantity(quantity);
			cart.setPrice((cart.getProduct().getPrice())*quantity);
			Cart updatedCart = dao.save(cart);
			return ResponseEntity.ok(updatedCart);
		}else {
			dao.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/carts/{id}")
	public ResponseEntity<?> deleteCart(@PathVariable("id") int id)
	{
		dao.deleteById(id); 
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/carts")
	public void deleteAllCart(){
		dao.deleteAll();
	}*/
}
