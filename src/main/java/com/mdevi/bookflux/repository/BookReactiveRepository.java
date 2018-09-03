package com.mdevi.bookflux.repository;

import com.mdevi.bookflux.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BookReactiveRepository extends ReactiveMongoRepository<Book, String> {


}
