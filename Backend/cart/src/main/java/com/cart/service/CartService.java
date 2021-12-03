package com.cart.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cart.bean.Cart;
import com.cart.dao.CartDao;
import com.cart.dto.CartDto;
import com.cart.dto.FoodItemDto;
@Service
public class CartService {

	@Autowired
	CartDao dao;
	@Autowired
	RestTemplate restTemplate;
	
	public Cart save(Cart cart)
	{
		cart.setCid(1);
		return dao.save(cart);
	}


	public void updatePriceAndQuantity(Cart temp) {
		dao.deleteById(temp.getCid());
		dao.save(temp);
	}

	public List<Cart> findAll() {
		return dao.findAll();
	}


	public CartDto delete(Integer id) {
		Optional<Cart> cart=dao.findById(id);
		dao.deleteById(id);
		return cart.get().convertEntityToDto();
	}
	
}

