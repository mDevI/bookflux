package com.mdevi.bookflux;

import com.mdevi.bookflux.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookfluxApplicationTests {

    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void testGetAllBooks() {
        webTestClient
                .get()
                .uri("/api/book")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json;charset=UTF-8")
                .expectBodyList(Book.class)
                .hasSize(6);
    }

    @Test
    public void testSaveNewBook() {
        Book theNewBook = new Book("9782343454345", "The test book", "Jonh Doe", "test", "Book test publisher", 2018, new String[]{"test"});
        webTestClient
                .post()
                .uri("/api/book")
                .body(Mono.just(theNewBook), Book.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType("application/json;charset=UTF-8")
                .expectBody(Book.class)
                .consumeWith(bookEntityExchangeResult -> assertTrue(bookEntityExchangeResult.getResponseBody().getGenre().equals(theNewBook.getGenre())));
    }





}
