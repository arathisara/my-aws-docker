package com.mysmartshop.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mysmartshop.cart.dto.Product;
import com.mysmartshop.cart.model.CartItems;

import com.mysmartshop.cart.repository.CartItemRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartItemRepository repo;

	@Autowired
	RestTemplate productServiceClient;

	@Override
	public List<CartItems> addToCart(String productId) {
		Optional<CartItems> checkItem = repo.findByProductId(productId);
		if (!checkItem.isPresent()) {
			CartItems item = new CartItems();
			item.setQuantity(1);
			item.setTotalPrice(fetchPrice(productId));
			repo.save(item);
		} else {
			CartItems item = checkItem.get();
			updateQuantity(productId, item.getQuantity() + 1);
		}
		return getAllItems();

	}

	@Override
	public List<CartItems> removeFromCart(String productId) {
		Optional<CartItems> checkItem = repo.findByProductId(productId);
		if (checkItem.isPresent()) {
			CartItems item = checkItem.get();
			repo.delete(item);
		}
		return getAllItems();
	}

	@Override
	public List<CartItems> updateQuantity(String productId, int quantity) {
		Optional<CartItems> checkItem = repo.findByProductId(productId);
		if (checkItem.isPresent()) {
			CartItems item = checkItem.get();
			float unitPrice = item.getTotalPrice() / item.getQuantity();
			item.setQuantity(quantity);
			item.setTotalPrice(unitPrice * quantity);
			repo.save(item);

		}
		return getAllItems();

	}

	@Override
	public float calculateTotalCost() {
		return repo.getTotalCartValue();
	}

	public List<CartItems> getAllItems() {
		return repo.findAll();
	}

	@CircuitBreaker(fallbackMethod = "fetchPriceFallback", name = "cb-product")
	private float fetchPrice(String productId) {

		Product product = productServiceClient.getForObject("http://product-ms/api/product/" + productId,
				Product.class);
		if (product != null)
			return product.getPrice();
		return 0;
	}

	private float fetchPriceFallback(String productId, Throwable t) {
		Product product = new Product(productId, "Dummy Product", 0, "A dummy product");
		System.err.println(t.getMessage());
		System.out.println("Response from Fallback");
		System.out.println(product);
		return product.getPrice();
	}

}
