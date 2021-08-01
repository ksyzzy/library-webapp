package com.example.librarybackend.services;

import com.example.librarybackend.models.Book;
import com.example.librarybackend.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();

    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
