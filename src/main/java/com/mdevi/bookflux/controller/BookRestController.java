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



/*    @GetMapping(value = "/page", produces = "application/json")
    public Mono<BookGrid> getAllBooksByPage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                            @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows,
                                            @RequestParam(value = "sidx", required = false) String sortBy,
                                            @RequestParam(value = "sord", required = false) String order) {

        LOG.info("GET. Page {} fetched with {} rows, sorted by {} in order by {}.", page, rows, sortBy, order);

        Flux<Book> bookPage = bookRepository
                .findAll()
                .skip(page * rows)
                .take(rows);

        Long countAllRecords = bookRepository.findAll().toStream().count();

        Long totalPages = countAllRecords % rows == 0 ? countAllRecords / rows : countAllRecords / rows + 1;

        // Construct the grid data that will return as JSON data
        BookGrid bookGrid = new BookGrid();
        bookGrid.setBooksData(bookPage);
        bookGrid.setCurrentPage(page);
        bookGrid.setTotalPages(totalPages.intValue());
        bookGrid.setTotalRecords(countAllRecords);
        Mono<BookGrid> bookGridMono = Mono.just(bookGrid);
        return  bookGridMono;
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
