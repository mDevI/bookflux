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
    private String[] authors;
    @NotBlank
    private String genre;
    private String publisher;
    private Date published;

    public Book() {
    }

    public Book(String id, String isbn, String title, String[] authors, String genre, String publisher) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
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

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
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
