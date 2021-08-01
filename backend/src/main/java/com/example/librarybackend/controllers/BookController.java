package com.example.librarybackend.controllers;

import com.example.librarybackend.helpers.ResponseBuilder;
import com.example.librarybackend.models.Book;
import com.example.librarybackend.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Book controller")
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
