//package com.example.bookservice.controller;
//
//public class BookController {
//
//}

package com.example.bookservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.bookservice.model.Book;
import com.example.bookservice.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return service.getBook(id);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return service.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return "Book deleted successfully";
    }

    @GetMapping("/{id}/availability")
    public boolean checkAvailability(@PathVariable Long id) {
        return service.checkAvailability(id);
    }

    @PutMapping("/{id}/reduce-stock")
    public Book reduceStock(
            @PathVariable Long id,
            @RequestParam Integer quantity) {

        return service.reduceStock(id, quantity);
    }
}
