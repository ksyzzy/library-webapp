package com.example.librarybackend.services;

import com.example.librarybackend.models.Book;
import com.example.librarybackend.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();

    }

    public Book getBookById(long id) {
        return bookRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public boolean checkIfExistsById(long id) {
        return bookRepository.existsById(id);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(long id, Book newBook) {
        bookRepository.updateBook(id, newBook.getAuthor(), newBook.getTitle(), newBook.getDescription(),newBook.getEan(),
                newBook.getPrice());
        return getBookById(id);
    }

    public boolean deleteBookById(long id) {
        bookRepository.deleteById(id);
        return !checkIfExistsById(id);
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
