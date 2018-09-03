package com.mdevi.bookflux.controller;

import com.mdevi.bookflux.model.Book;
import com.mdevi.bookflux.repository.BookReactiveRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController("/api/book")
public class BookRestController {

    private final BookReactiveRepository bookRepository;

    public BookRestController(BookReactiveRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Book> createBook(@RequestBody Mono<Book> book) {
        return book.flatMap(bookRepository::save);
    }

    @GetMapping("/{id}")
    public Mono<Book> getBookById(@PathVariable String id) {
        return bookRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteBook(@PathVariable String id) {
        return bookRepository.deleteById(id);
    }

}
