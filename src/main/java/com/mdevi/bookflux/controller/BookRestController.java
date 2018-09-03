package com.mdevi.bookflux.controller;


import com.mdevi.bookflux.model.Book;
import com.mdevi.bookflux.model.BookGrid;
import com.mdevi.bookflux.repository.BookReactiveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book")
public class BookRestController {

    private final BookReactiveRepository bookRepository;

    private static final Logger LOG = LoggerFactory.getLogger(BookReactiveRepository.class);

    public BookRestController(BookReactiveRepository bookRepository) {
        this.bookRepository = bookRepository;
    }



/*    @GetMapping(value = "/page", produces = "application/json")
    public BookGrid getAllBooksByPage(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                      @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows,
                                      @RequestParam(value = "sidx", required = false) String sortBy,
                                      @RequestParam(value = "sord", required = false) String order) {

        LOG.info("GET. Page {} fetched with {} rows, sorted by {} in order by {}.", page, rows, sortBy, order);
        Sort sort = null;
        String orderBy = sortBy;

        if (orderBy != null && order != null) {
            if (order.equals("desc")) {
                sort = new Sort(Sort.Direction.DESC, orderBy);
            } else
                sort = new Sort(Sort.Direction.ASC, orderBy);
        }


        // Constructs page request for current page
        // Note: page number for Spring Data JPA starts with 0,
        // while jqGrid starts with 1
*//*        PageRequest pageRequest = null;
        if (sort != null) {
            pageRequest = PageRequest.of(page - 1, rows, sort);
        } else {
            pageRequest = PageRequest.of(page - 1, rows);
        }*//*
        Flux<Book> bookPage = bookRepository
                .findAll(sort)
                .skip(page * rows)
                .take(rows);


        // Construct the grid data that will return as JSON data
        BookGrid bookGrid = new BookGrid();
        bookGrid.setBooksData(bookPage.toStream().collect(Collectors.toList()));
        bookGrid.setCurrentPage(bookPage.getNumber() + 1);
        bookGrid.setTotalPages(bookPage.getTotalPages());
        bookGrid.setTotalRecords(bookRep);
        return bookGrid;
    }*/

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
