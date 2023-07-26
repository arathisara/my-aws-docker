package com.mysmartshop.order.service;

import java.util.List;
import java.util.Random;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mysmartshop.order.model.CartItems;
import com.mysmartshop.order.model.Order;
import com.mysmartshop.order.repository.OrderRepository;


 

@Service
public class OderServiceImpl implements OrderService {

 

    @Autowired
    OrderRepository orderRepo;

 

//    @Autowired
//    RestTemplate cartServiceClient;

 

    @Override
    public Order getOrderDetails(String orderId) {
        return orderRepo.findByOrderId(orderId);
    }

    @Override
    public Order placeOrder(List<CartItems> cartItems) {
        Order orderDetails = new Order();
        orderDetails.setOrderItems(cartItems);
        orderDetails.setStatus("Order Placed");
        Random rnd = new Random();
        int num = rnd.nextInt(900000)+100000;
        String str = "n"+System.currentTimeMillis()+num;
        orderDetails.set_id(num);
        orderDetails.setOrderId(str);
        return orderRepo.save(orderDetails);
    }

 

    @Override
    public void cancelOrder(String orderId) {
        Order order=getOrderDetails(orderId);
        orderRepo.deleteById(order.get_id());
    }

 

    @Override
    public List<Order> getAllOrders(){
        return orderRepo.findAll();
    }

 

    @Override
    public void updateOrderDetails(String orderId, String status) {
        Order order= getOrderDetails(orderId);
        order.setOrderId(orderId);
        order.setStatus(status);
        orderRepo.save(order);
    }

//    @Override
//    public void updateOrderDetails(String orderId, String status) {
//        // TODO Auto-generated method stub
//        
//    }
//
//    @Override
//    public void cancelOrder(String orderId) {
//        // TODO Auto-generated method stub
//        
//    }

 

//    @Override
//    public Order placeOrder(List<CartItem> CartItems) {
//        List<CartItem> cartItemsFetched = fetchCartItems();
//        String orderId = generateOrderId();
//        String status="Order Placed";
//        Order placedOrder =new Order(0, orderId, status, cartItemsFetched);
//        return placedOrder;
//    }
//
//    @Override
//    public Order getOrderDetails(String orderId) {
//        return orderRepo.findById(orderId).get();
//    }
//
//    @Override
//    public void updateOrderDetails(String orderId, String status) {
//        Order order= getOrderDetails(orderId);
//        order.setOrderId(orderId);
//        order.setStatus(status);
//        orderRepo.save(order);
//    }
//
//    @Override
//    public void cancelOrder(String orderId) {
//        orderRepo.deleteById(orderId);
//    }
//
//    public List<CartItem> fetchCartItems() {
//        List<CartItem> cartItems = (List<CartItem>) cartServiceClient.getForObject("http://cart-ms/api/cart/getall",
//                CartItem.class);
//        return cartItems;
//
//    }
//
//    public String generateOrderId() {
//        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//        Random random = new Random();
//        StringBuilder randomId = new StringBuilder();
//
//        for (int i = 0; i < 6; i++) {
//            int randomIndex = random.nextInt(CHARACTERS.length());
//            char randomChar = CHARACTERS.charAt(randomIndex);
//            randomId.append(randomChar);
//        }
//
//        return randomId.toString();
//
//    }

 

}

//import java.util.List;
//import java.util.Random;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.mysmartshop.order.model.CartItems;
//import com.mysmartshop.order.model.Order;
//import com.mysmartshop.order.repository.OrderRepository;
//
//@Service
//public class OderServiceImpl  implements OrderService{
//
//	@Autowired
//	private OrderRepository repo;
//	
//	@Override
//	public Order getOrderDetails(String orderId) {
//		return repo.findByOrderId(orderId);
//	}
//	
//	@Override
//	public Order placeOrder(List<CartItems> cartItems) {
//		Order orderDetails = new Order();
//		orderDetails.setOrderItems(cartItems);
//		orderDetails.setStatus("Order Placed");
//		Random rnd = new Random(100000);
//		String str = "n"+System.currentTimeMillis()+Math.abs(rnd.nextLong());
//		orderDetails.set_id(Math.abs(rnd.nextInt()));
//		orderDetails.setOrderId(str);
//		return repo.save(orderDetails);
//	}
//	
//}
