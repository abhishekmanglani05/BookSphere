//package com.example.orderservice.controller;
//
//public class OrderController {
//
//}


package com.example.orderservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return service.placeOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return service.getOrder(id);
    }

    @GetMapping("/customer/{customerName}")
    public List<Order> getOrdersByCustomer(
            @PathVariable String customerName) {

        return service.getOrdersByCustomer(customerName);
    }
}
