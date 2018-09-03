package com.mdevi.bookflux.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "books")
public class Book {

    @Id
    private String id;
    @Size(min = 13, max = 13)
    private String isbn;
    @NotBlank
    @Size(min = 2, max = 150)
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    private String genre;
    private String publisher;
    private Date published;

    public Book() {
    }

    public Book(String id, String isbn, String title, String author, String genre, String publisher) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
    }

    public Book(@Size(min = 13, max = 13) String isbn, @NotBlank @Size(min = 2, max = 150) String title, @NotBlank String author, @NotBlank String genre, String publisher) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }
}
