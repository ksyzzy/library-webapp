package com.example.librarybackend;

import com.example.librarybackend.models.Book;
import com.example.librarybackend.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;


import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryBackendApplicationTests {

	@Autowired
	private BookRepository bookRepository;

	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	public void givenNoDataInDatabase_whenRetrieveAllEntities_thenNone() {
		List<Book> bookList = bookRepository.findAll();
		assertEquals(bookList.size(), 0);
	}

	@Test
	@Transactional
	public void givenBookRepository_whenSaveAndRetrieveEntity_thenOK() {
		Book newBook = bookRepository.save(new Book(1, "author", "title", 35, "description", "111111111111"));
		Book foundBook = bookRepository.getById(newBook.getId());
		assertNotNull(foundBook);
		assertEquals(newBook, foundBook);
	}

	@Test
	@Transactional
	public void givenNoSuchEntity_whenDeleteEntity_thenExceptionThrown() {
		Book newBook = bookRepository.save(new Book(1, "author", "title", 35, "description", "1111111111111"));
		Book foundBook = bookRepository.getById(newBook.getId());
		assertTrue(bookRepository.existsById(foundBook.getId()));
		bookRepository.deleteById(foundBook.getId());
		assertThrows(JpaObjectRetrievalFailureException.class, () -> bookRepository.getById(foundBook.getId()));
	}

	@Test
	@Transactional
	public void givenNotValidEAN_whenAddToDatabase_thenExceptionThrown() {
		Book newBook = bookRepository.save(new Book(2, "author", "title", 35, "description", "A1111111111"));
		assertThrows(ConstraintViolationException.class, () ->  bookRepository.existsById(newBook.getId()));
	}

	@Test
	@Transactional
	public void givenExistingEntity_whenUpdateEntity_thenOK() {
		Book book = bookRepository.save(new Book(1, "author", "title", 35, "description", "1111111111111"));
		book.setTitle("newTitle");
		bookRepository.updateBook(book.getId(), book.getAuthor(), book.getTitle(), book.getDescription(), book.getEan(), book.getPrice());
		Book newBook = bookRepository.getById(book.getId());
		assertEquals(book.getTitle(), newBook.getTitle());
	}
}
