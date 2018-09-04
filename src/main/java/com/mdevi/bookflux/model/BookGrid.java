package com.mdevi.bookflux.model;

import reactor.core.publisher.Flux;

public class BookGrid {
    private int totalPages;
    private int currentPage;
    private long totalRecords;
    private Flux<Book> booksData;

    public BookGrid() {
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Flux<Book> getBooksData() {
        return booksData;
    }

    public void setBooksData(Flux<Book> booksData) {
        this.booksData = booksData;
    }
}
