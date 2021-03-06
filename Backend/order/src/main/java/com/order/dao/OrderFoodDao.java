package com.order.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.order.bean.OrderFood;

@Repository
public interface OrderFoodDao extends JpaRepository<OrderFood, Integer>{

	
	@Modifying
	@Query("Select f from OrderFood f where f.orderId=?1")
	List<OrderFood> findOrderFoodByOrderId(Integer orderId);

	/*@Query("insert into OrderFood (orderId,id) values (:orderId,:id)")
	void saveOrderFood(@Param("orderId") Integer orderId,@Param("id") Integer id);*/

}
