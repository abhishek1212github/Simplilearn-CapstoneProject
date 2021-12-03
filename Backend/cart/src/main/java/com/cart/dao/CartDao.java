package com.cart.dao;

import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cart.bean.Cart;
@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

	/*@Modifying
	@Transactional
	@Query("Update Cart c set c.quantity=?2,c.price=?1 where c.cid=?3")
	void updateQuantity555(Long i,Integer t,Double d);
*/
}
