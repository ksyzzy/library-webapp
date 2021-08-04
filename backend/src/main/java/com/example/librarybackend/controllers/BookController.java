package com.example.librarybackend.controllers;

import com.example.librarybackend.configuration.SwaggerConfig;
import com.example.librarybackend.enums.ErrorCode;
import com.example.librarybackend.helpers.ResponseBuilder;
import com.example.librarybackend.models.Book;
import com.example.librarybackend.services.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Api(value = "Book controller", tags = {SwaggerConfig.BOOK_TAG})
@RestController
public class BookController {

    private BookService bookService;

    private ResponseBuilder responseBuilder;

    @ApiOperation(value = "Get list of all books in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK", response = Book.class, responseContainer = "List")
    })
    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllBooks() {
        return responseBuilder.build(bookService.getAllBooks(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get book by id from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK", response = Book.class),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping(value = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBookById(@ApiParam(
            name = "id",
            type = "long",
            required = true,
            value = "ID of the book in database"
    ) @PathVariable long id) {
        try {
            return bookService.checkIfExistsById(id) ?
                    responseBuilder.build(bookService.getBookById(id), HttpStatus.OK)
                    : responseBuilder.build(ErrorCode.ENTITY_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException e) {
            return responseBuilder.build(ErrorCode.ENTITY_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Add new book to the database")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Entity created successfully", response = Book.class),
            @ApiResponse(code = 409, message = "Conflict")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewBook(@ApiParam(
            name = "newBook",
            type = "BookReq",
            required = true,
            value = "New book to be added to database"
    ) @Valid @RequestBody Book book) {
        try {
            book = bookService.addBook(book);
            return responseBuilder.build(book, HttpStatus.CREATED);
        } catch (Exception e) {
            return responseBuilder.build(ErrorCode.GENERIC_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update book by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK|Success", response = Book.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @PutMapping(value = "/books/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateBookById(@ApiParam(
            name = "id",
            type = "long",
            required = true,
            value = "ID of the book in database"
    ) @PathVariable long id, @ApiParam(
            name = "newBook",
            type = "BookReq",
            required = true,
            value = "Updated book to be saved in the database"
    ) @Valid @RequestBody Book newBook) {
        try {
            if (bookService.checkIfExistsById(id)) {
                newBook = bookService.updateBook(id, newBook);
                return responseBuilder.build(newBook, HttpStatus.OK);
            } else {
                throw new NoSuchElementException("Book with specified ID does not exist in database");
            }
        } catch (NoSuchElementException e) {
            return responseBuilder.build(ErrorCode.ENTITY_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Delete book by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK|Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @DeleteMapping(value = "/books/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteBookById(@ApiParam(
            name = "id",
            type = "long",
            required = true,
            value = "ID of the book in database"
    ) @PathVariable long id) {
        try {
            if (bookService.checkIfExistsById(id)) {
                return bookService.deleteBookById(id) ?
                        responseBuilder.build("User deleted successfully", HttpStatus.OK)
                        : responseBuilder.build(ErrorCode.ENTITY_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            return responseBuilder.build(ErrorCode.ENTITY_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
        }
    }

    public BookService getBookService() {
        return bookService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public ResponseBuilder getResponseBuilder() {
        return responseBuilder;
    }

    @Autowired
    public void setResponseBuilder(ResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }
}
