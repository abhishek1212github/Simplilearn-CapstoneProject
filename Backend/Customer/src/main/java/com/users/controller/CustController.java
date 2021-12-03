package com.users.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.users.bean.Customer;
import com.users.dao.CustDao;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustController {
	@Autowired
	CustDao dao;

		
		@CrossOrigin
		@PostMapping("/")
		public Customer addCustomer(@RequestBody Customer customer, HttpSession session) {
			session.setAttribute("cust_email", customer.getEmail());
			return dao.save(customer);
		}
		
		@SuppressWarnings("rawtypes")
		@CrossOrigin(origins = "http://localhost:4200")
		@PostMapping("/{email}")
		public boolean verifyLogin(@RequestBody Map loginData, @PathVariable(name = "email") String email, HttpSession session) {
			String lemail = (String) loginData.get("email");
			String lpassword = (String) loginData.get("password");
			System.err.println(lemail+"   "+lpassword);
			Customer customer = dao.findByEmail(email);
			System.err.println(customer.getEmail()+"   "+customer.getPassword());
			if(customer!= null && customer.getEmail().equals(lemail) && customer.getPassword().equals(lpassword)) {
				session.setAttribute("cust_email", lemail);
				return true;
			}else {
				return false; 
			}
		}
		
		@GetMapping("/")
		public List<Customer> getAllCustomers(){
			return dao.findAll();
		}
		
		/*@GetMapping("/search/{keyword}")
		public List<Customer> searchCustomer(@PathVariable String keyword){
			return dao.searchCustomer(keyword);
		}*/
		
		@DeleteMapping("/{email}")
		public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable String email){
			Customer customer = dao.findByEmail(email);
			dao.delete(customer);
			Map<String, Boolean> map = new HashMap<>();
			map.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(map);
		}
		
		@GetMapping("/{cust_email}")
		public Customer getCustomer(@PathVariable String cust_email) {
			return dao.findByEmail(cust_email);
			
		}
	}

