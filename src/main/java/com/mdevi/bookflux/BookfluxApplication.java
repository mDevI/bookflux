package com.mdevi.bookflux;

import com.mdevi.bookflux.model.Book;
import com.mdevi.bookflux.repository.BookReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BookfluxApplication implements CommandLineRunner {

    private final BookReactiveRepository bookReactiveRepository;

    @Autowired
    public BookfluxApplication(BookReactiveRepository bookReactiveRepository) {
        this.bookReactiveRepository = bookReactiveRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookfluxApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        bookReactiveRepository.deleteAll()
                .subscribe(null, null, () -> Stream.of(
                        new Book(UUID.randomUUID().toString(), "9782343454567", "The moon light", "Jonh Doe", "Art", "Book PUB"),
                        new Book(UUID.randomUUID().toString(), "9782356554765", "A trip to spring", "Katrin Gail", "Fiction", "ABC Publishing"),
                        new Book(UUID.randomUUID().toString(), "9768765342456", "The Spring warrior", "Mike Doe", "History", "ABC Publishing"),
                        new Book(UUID.randomUUID().toString(), "9774567234562", "The best of programming", "Aaron Fisher", "Science", "ABC Publishing"),
                        new Book(UUID.randomUUID().toString(), "9779876654222", "With pleasure to make thing.", "Nick Mall", "Fiction", "ABC Publishing"),
                        new Book(UUID.randomUUID().toString(), "9788763458762", "Go to the finish first.", "Brian Doodle", "Fiction", "ABC Publishing")
                )
                        .forEach(book -> bookReactiveRepository.save(book).subscribe()
                        ));

    }
}
