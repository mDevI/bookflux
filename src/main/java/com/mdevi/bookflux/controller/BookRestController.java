package com.mdevi.bookflux.controller;

import com.mdevi.bookflux.model.Book;
import com.mdevi.bookflux.repository.BookReactiveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/book")
public class BookRestController {

    private final BookReactiveRepository bookRepository;

    private static final Logger LOG = LoggerFactory.getLogger(BookReactiveRepository.class);

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
    public Mono<ResponseEntity<Book>> getBookById(@PathVariable(value = "id") String id) {
        return bookRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Book>> updateBook(@PathVariable(value = "id") String bookId, @RequestBody Book book) {
        return bookRepository
                .findById(bookId)
                .flatMap(bookExist -> {
                    bookExist.setAuthor(book.getAuthor());
                    bookExist.setGenre(book.getGenre());
                    bookExist.setIsbn(book.getIsbn());
                    bookExist.setTitle(book.getTitle());
                    bookExist.setPublisher(book.getPublisher());
                    bookExist.setPublishedYear(book.getPublishedYear());
                    return bookRepository.save(bookExist);
                })
                .map(updatedBook -> new ResponseEntity<>(updatedBook, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteBook(@PathVariable String id) {
        return bookRepository
                .findById(id)
                .flatMap(bookToDelete -> bookRepository.delete(bookToDelete).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
