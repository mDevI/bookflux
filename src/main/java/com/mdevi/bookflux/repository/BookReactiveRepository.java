package com.mdevi.bookflux.repository;

import com.mdevi.bookflux.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface BookReactiveRepository extends ReactiveMongoRepository<Book, String> {

    Flux<Book> findAllByTitleContainsOrderByTitle(String title);

    Flux<Book> findAllByAuthorContainsOrderByTitle(String author);
}
