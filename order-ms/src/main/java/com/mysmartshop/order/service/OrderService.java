package com.mysmartshop.order.service;

import java.util.List;
import com.mysmartshop.order.model.CartItems;
import com.mysmartshop.order.model.Order;

 

public interface OrderService {
    public Order placeOrder(List<CartItems> cartItems);

 

    public Order getOrderDetails(String orderId);

 

    public void updateOrderDetails(String orderId, String status);

 

    public void cancelOrder(String orderId);

 

    public List<Order> getAllOrders();
}

 

//+placeOrder(cartItems: List<CartItem>): Order
//+getOrderDetails(orderId: String): Order
//+updateOrderStatus(orderId: String, status: String): void
//+cancelOrder(orderId: String): void

//import java.util.List;
//
//
//import com.mysmartshop.order.model.CartItems;
//import com.mysmartshop.order.model.Order;
//
//public interface OrderService {
//	
//	public Order placeOrder(List<CartItems> cartItems);
//	
//	public Order getOrderDetails(String orderId);
//	
//	
//	
//	
//}
