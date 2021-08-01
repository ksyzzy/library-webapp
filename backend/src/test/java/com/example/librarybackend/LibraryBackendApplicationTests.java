package com.example.librarybackend;

import com.example.librarybackend.models.Book;
import com.example.librarybackend.repositories.BookRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

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
	public void givenNoSuchEntity_whenDeleteEntity_thenExceptionThrows() {
		Book newBook = bookRepository.save(new Book(1, "author", "title", 35, "description", "111111111111"));
		Book foundBook = bookRepository.getById(newBook.getId());

		assertTrue(bookRepository.existsById(foundBook.getId()));
		bookRepository.deleteById(foundBook.getId());
		assertThrows(JpaObjectRetrievalFailureException.class, () -> bookRepository.getById(foundBook.getId()));
	}

}
