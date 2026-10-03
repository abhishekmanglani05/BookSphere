//package com.example.orderservice.service;
//
//public class OrderService {
//
//}


package com.example.orderservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.orderservice.client.BookClient;
import com.example.orderservice.dto.Book;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookClient bookClient;

    public OrderService(OrderRepository orderRepository,
                        BookClient bookClient) {
        this.orderRepository = orderRepository;
        this.bookClient = bookClient;
    }

    public Order placeOrder(Order order) {

        Book book = bookClient.getBook(order.getBookId());

        if (book == null) {
            return null;
        }

        if (book.getQuantity() < order.getQuantity()) {
            return null;
        }

        Double totalPrice =
                book.getPrice() * order.getQuantity();

        order.setTotalPrice(totalPrice);
        order.setOrderStatus("CONFIRMED");

        bookClient.reduceStock(
                order.getBookId(),
                order.getQuantity());

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrdersByCustomer(String customerName) {
        return orderRepository.findByCustomerName(customerName);
    }
}
