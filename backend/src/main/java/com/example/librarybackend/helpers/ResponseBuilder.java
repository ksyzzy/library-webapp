package com.example.librarybackend.helpers;

import com.example.librarybackend.enums.ErrorCode;
import com.example.librarybackend.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseBuilder {
    private JSONBuilder jsonBuilder;

    public ResponseEntity<String> build(Book book, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(jsonBuilder.buildJSON(book));
    }

    public ResponseEntity<String> build(List<Book> bookList, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(jsonBuilder.buildJSON(bookList));
    }

    public ResponseEntity<String> build(ErrorCode errorCode, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(jsonBuilder.buildJSON(errorCode));
    }

    public ResponseEntity<String> build(String value, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(jsonBuilder.buildJSON(value));
    }

    public JSONBuilder getJsonBuilder() {
        return jsonBuilder;
    }

    @Autowired
    public void setJsonBuilder(JSONBuilder jsonBuilder) {
        this.jsonBuilder = jsonBuilder;
    }
}
