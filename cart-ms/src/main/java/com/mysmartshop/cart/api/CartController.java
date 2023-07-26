package com.mysmartshop.cart.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysmartshop.cart.dto.CartDetails;
import com.mysmartshop.cart.model.CartItems;
import com.mysmartshop.cart.service.CartService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/cart")
public class CartController {
	
//	RestTemplate restClient = new RestTemplate();
//	@Autowired
//	RestTemplate restClient;
	
	@Autowired
	private CartService cartService;
	
	@GetMapping
	public String greet() {
		return "Message from Cart Service";
	}
	
//	@GetMapping("/product")
//	public String fetchProductGreetMessage() {
//		String msg=restClient.getForObject("http://localhost:8100/product", String.class);
//		return "Message received from Product Service : "+msg;
//	}
	@GetMapping("/items")
	public CartDetails getCartDetails() {
		List<CartItems> items=cartService.getAllItems();
		float totalCartValue=cartService.calculateTotalCost();
		CartDetails cartDetails=new CartDetails();
		cartDetails.setItemList(items);
		cartDetails.setTotalCartValue(totalCartValue);
		return cartDetails;
	}
	
	@PostMapping("/items/product/{productId}")
	@CircuitBreaker(fallbackMethod = "addNewItemFallback", name = "cb-product")
	public List<CartItems> addNewItem(@PathVariable String productId){
		return cartService.addToCart(productId);
	}
	
	
	public List<CartItems> addNewItemFallback(@PathVariable String productId, Throwable t){
		System.err.println(t.getMessage());
		return new ArrayList<CartItems>();
	}
	

	
	@DeleteMapping("/items/product/{productId}")
	public List<CartItems> deleteItem(@PathVariable String productId){
		return cartService.removeFromCart(productId);
	}
	

}
