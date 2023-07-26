package com.mysmartshop.order.api;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 

import com.mysmartshop.order.model.Order;
import com.mysmartshop.order.service.OrderService;

 

@RestController
@RequestMapping("/api/order")
public class OrderController {

 

    @Autowired
    OrderService orderService;

 

    @GetMapping
    public String greet() {
        return "Message from order Service";
    }

 

    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order.getOrderItems());
    }

 

    @GetMapping("/{orderId}")
    public Order getOrderDetails(@PathVariable String orderId) {
        return orderService.getOrderDetails(orderId);
    }

    @DeleteMapping("/{orderId}")
    public void cancelOrder(@PathVariable String orderId) {
        orderService.cancelOrder(orderId);    
    }

 

    @GetMapping("/getall")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PutMapping("/update/{orderId}/{status}")
    public void updateOrderDetails(@PathVariable String orderId, @PathVariable String status) {
        orderService.updateOrderDetails(orderId, status);
    }










//    @PostMapping("/placeorder")
//    public Order placeOrder() {
//        return orderService.placeOrder();
//
//    }
//
//    @GetMapping("/{orderId}")
//    public Order getOrderDetails(@PathVariable String orderId) {
//        return orderService.getOrderDetails(orderId);
//    }
//
//    @PutMapping("/update/{orderId}/{status}")
//    public void updateOrderDetails(@PathVariable String orderId, @PathVariable String status) {
//        orderService.updateOrderDetails(orderId, status);
//    }
//

 

}







//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//@RestController
//@RequestMapping("/order")
//public class OrderController {
//	@Autowired
//	RestTemplate restClient;
//	
//	@GetMapping
//	public String greet() {
//		return "Message from Order Service";
//	}
//	
//	@GetMapping("/cart")
//	public String fetchCartGreetMessage() {
//		String msg=restClient.getForObject("http://localhost:8200/cart", String.class);
//		return "Message received from Cart Service : "+msg;
//	}
//	
//	
//
//}
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mysmartshop.order.model.Order;
//import com.mysmartshop.order.service.OrderService;
//
//@RestController
//@RequestMapping("/api/order")
//public class OrderController {
//	
//	@Autowired
//	private OrderService orderService;
//	
//	@GetMapping
//	public String greet() {
//		return "Message from Order Service";
//	}
//	
//	@PostMapping
//	public Order placeOrder(@RequestBody Order order) {
//		return orderService.placeOrder(order.getOrderItems());
//	}
//	
//	@GetMapping("/{orderId}")
//	public Order getOrderDetails(@PathVariable String orderId) {
//		return orderService.getOrderDetails(orderId);
//	}
//
//}


