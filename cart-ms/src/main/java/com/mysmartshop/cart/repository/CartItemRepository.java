package com.mysmartshop.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mysmartshop.cart.model.CartItems;

public interface CartItemRepository extends JpaRepository<CartItems, Integer>{
	
	public Optional<CartItems> findByProductId(String productId);
	@Query(value="select sum(total_price) from cart_item", nativeQuery = true)
	public float getTotalCartValue();

}
