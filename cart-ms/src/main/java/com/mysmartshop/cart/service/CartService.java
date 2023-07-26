package com.mysmartshop.cart.service;

import java.util.List;

import com.mysmartshop.cart.model.CartItems;

public interface CartService {
	
	public List<CartItems> addToCart(String productId);
	public List<CartItems> removeFromCart(String productId);
	public List<CartItems> updateQuantity(String productId,int quantity);
	public float calculateTotalCost();
	public List<CartItems> getAllItems();

}
