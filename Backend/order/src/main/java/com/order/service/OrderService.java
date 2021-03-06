package com.order.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.order.bean.Order;
import com.order.bean.OrderFood;
import com.order.dao.OrderDao;
import com.order.dao.OrderFoodDao;
import com.order.dto.FoodItemDto;
import com.order.dto.OrderDto;

@Service
public class OrderService {

	@Autowired
	OrderDao orderDao;
	@Autowired
	OrderFoodDao orderFoodDao;
	@Autowired
	RestTemplate restTemplate;
	public boolean saveOrder(OrderDto orderDto)
	{
		OrderFood orderFood;
		for(FoodItemDto dto:orderDto.getFoods())
		{
			orderFood=new OrderFood();
			orderFood.setId(dto.getId());orderFood.setOrderId(orderDto.getOrderId());
			orderFoodDao.saveAndFlush(orderFood);
			System.err.println(orderFood);
		}
		if(orderDao.saveAndFlush(orderDto.convertFromDtoToEntity())!=null)	return true;
		return false;
	}
	
	public List<OrderDto> getAllOrders()
	{
		List<Order> orders=orderDao.findAll();
		FoodItemDto foodItemDto[]=restTemplate.getForObject("http://localhost:8082/foodbox/getAllFoods",FoodItemDto[].class);
		List<FoodItemDto> foodItemDtos=Arrays.asList(foodItemDto); 
		List<OrderDto> orderDtos= new ArrayList<OrderDto>();
		List<OrderFood> orderFoods;
		List<FoodItemDto> itemDtos=null;

		
		for(Order order:orders)
		{
			orderFoods=orderFoodDao.findOrderFoodByOrderId(order.getOrderId());
			itemDtos= new ArrayList<FoodItemDto>();
			for(OrderFood o:orderFoods)
			{
				
				for(FoodItemDto f:foodItemDtos)
				{
					if(f.getId()==o.getId())
					{
						itemDtos.add(f);
						break;
					}
				}
				
			}
			orderDtos.add(order.convertFromEntityToDto(order, itemDtos));
		}
		return orderDtos;
	}
}
