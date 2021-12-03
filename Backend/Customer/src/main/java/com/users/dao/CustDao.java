package com.users.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.users.bean.Customer;
@Repository
public interface CustDao extends JpaRepository<Customer, Integer> {

	/*@Query("Select c from Customer c where c.email=?1")
	Customer getCustomerByEmail(String email);*/
	@Query("Select c from Customer c where email=:email ")
    Customer findByEmail(@Param("email") String email);
	
	/*@Query("SELECT c FROM Customer c WHERE c.email LIKE %?1%"
			+" OR c.name LIKE %?1%"
			+" OR c.contact LIKE %?1%"
			+" OR c.address LIKE %?1%")
	public List<Customer> searchCustomer(String keyword);*/

}
