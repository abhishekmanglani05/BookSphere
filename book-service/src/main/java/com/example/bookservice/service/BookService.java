//package com.example.bookservice.service;
//
//public class BookService {
//
//}


package com.example.bookservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookservice.model.Book;
import com.example.bookservice.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book addBook(Book book) {
        return repository.save(book);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBook(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Book updateBook(Book book) {
        return repository.save(book);
    }

    public void deleteBook(Long id) {
        repository.deleteById(id);
    }

    public boolean checkAvailability(Long id) {

        Book book = repository.findById(id).orElse(null);

        if (book == null) {
            return false;
        }

        return book.getQuantity() > 0;
    }

    public Book reduceStock(Long id, Integer quantity) {

        Book book = repository.findById(id).orElse(null);

        if (book == null) {
            return null;
        }

        if (book.getQuantity() < quantity) {
            return null;
        }

        book.setQuantity(book.getQuantity() - quantity);

        return repository.save(book);
    }
}
