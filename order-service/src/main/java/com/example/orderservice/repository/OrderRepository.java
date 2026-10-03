//package com.example.orderservice.repository;
//
//public class OrderRepository {
//
//}

package com.example.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerName(String customerName);
}
