//package com.example.orderservice.client;
//
//public class BookClient {
//
//}


package com.example.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.orderservice.dto.Book;

@FeignClient(name = "BOOK-SERVICE")
public interface BookClient {

    @GetMapping("/books/{id}")
    Book getBook(@PathVariable Long id);

    @GetMapping("/books/{id}/availability")
    boolean checkAvailability(@PathVariable Long id);

    @PutMapping("/books/{id}/reduce-stock")
    Book reduceStock(
            @PathVariable Long id,
            @RequestParam Integer quantity);
}
