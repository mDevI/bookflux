package com.mdevi.bookflux.repository;

import com.mdevi.bookflux.model.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.print.Pageable;

public interface BookReactiveRepository extends ReactiveMongoRepository<Book, String> {

    Flux<Book> findAll(Sort sort);

    Mono<Long> countAll();
}
