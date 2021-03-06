package com.order.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.dao.OrderDao;
import com.order.dto.CartDto;
import com.order.dto.OrderDto;
import com.order.service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

	@Autowired
	private OrderService serv;
	
	@PostMapping()
	public ResponseEntity<String> saveOrder(@RequestBody OrderDto order)
	{
		if(serv.saveOrder(order))  return new ResponseEntity<String>("Order Placed",HttpStatus.OK);
		return new ResponseEntity<String>("Failed",HttpStatus.BAD_REQUEST);
	}
	

	
	
	@GetMapping("/admin")
	public List<OrderDto> getAllOrders() {
		return serv.getAllOrders();		
	}
}
