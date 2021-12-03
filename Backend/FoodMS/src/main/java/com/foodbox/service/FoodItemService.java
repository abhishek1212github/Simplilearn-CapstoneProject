package com.foodbox.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodbox.bean.FoodItem;
import com.foodbox.dao.FoodItemDao;

@Service
public class FoodItemService {

	@Autowired
	private FoodItemDao dao;

	public FoodItem getFoodItemById(Integer id) {
		return dao.findById(id).get();
	}
	
	public FoodItem getFoodItemByName(String name) {
		return dao.findByName(name);
	}
	
	public List<FoodItem> getAllFoodItems() {
		return dao.findAll();
	}

	public FoodItem saveFoodItem(FoodItem item) {
		return dao.saveAndFlush(item);
	}

	public List<FoodItem> getFoodByCuisine(String cuisine) {
		return dao.findByCuisine(cuisine);
	}

	public Map<String, List<FoodItem>> getFoodCategorisedByCuisine() {
		List<FoodItem> items=dao.findAll();
		List<FoodItem> chinese=new ArrayList<FoodItem>();
		List<FoodItem> indian=new ArrayList<FoodItem>();
		List<FoodItem> punjabi=new ArrayList<FoodItem>();
		List<FoodItem> continental=new ArrayList<FoodItem>();
		List<FoodItem> spanish=new ArrayList<FoodItem>();
		List<FoodItem> french=new ArrayList<FoodItem>();
		
		for(FoodItem item:items) {
			if(item.getCategory().equalsIgnoreCase("Chinese"))
				chinese.add(item);
			else if(item.getCategory().equalsIgnoreCase("Indian"))
				indian.add(item);
			else if(item.getCategory().equalsIgnoreCase("Continental"))
				continental.add(item);
			else if(item.getCategory().equalsIgnoreCase("spanish"))
				spanish.add(item);
			else if(item.getCategory().equalsIgnoreCase("French"))
				french.add(item);
			else if(item.getCategory().equalsIgnoreCase("Punjabi"))
				punjabi.add(item);
			
		}
		Map<String,List<FoodItem>> map=new HashMap<String, List<FoodItem>>();
		map.put("Chinese", chinese);
		map.put("Indian", indian);
		map.put("Punjabi", punjabi);
		map.put("Continental", continental);
		map.put("Spanish", spanish);
		map.put("French", french);
		return map;
	}

	public boolean deleteFoodItem(Integer id) {
		try {
			dao.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean enableDisablefoodItem(Integer id, Boolean enabled) {
		if(dao.updateFoodStatus(id,enabled)==1)
		return true;
		return false;
	}
	
	public boolean updateFoodName(Integer id,String name) {
		if(dao.updateFoodName(id, name)==1)
			return true;
		return false;
	}
	
	public boolean updateFoodCategory(Integer id,String category) {
		if(dao.updateFoodCategory(id, category)==1)
			return true;
		return false;
	}
	
	public boolean updateFoodPrice(Integer id,Double price) {
		if(dao.updateFoodPrice(id, price)==1)
			return true;
		return false;
	}
	
	public boolean updateFoodDiscount(Integer id,Double discount) {
		if(dao.updateFoodDiscount(id, discount)==1)
			return true;
		return false;
	}
	
	public boolean updateFoodDescription(Integer id,String desc) {
		if(dao.updateFoodDescription(id, desc)==1)
			return true;
		return false;
	}
	
}
