package com.example.librarybackend.helpers;

import com.example.librarybackend.enums.ErrorCode;
import com.example.librarybackend.models.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class JSONBuilder {
    private final HashMap<ErrorCode, String> errorsList;
    private ObjectMapper mapper;

    public JSONBuilder() {
        HashMap<ErrorCode, String> errorsList = new HashMap<>();
        errorsList.put(ErrorCode.JSON_PARSE_ERROR, "Could not parse the result");
        this.errorsList = errorsList;
    }

    public String buildJSON(Book book) {
        try {
            return this.mapper.writeValueAsString(book);
        } catch (JsonProcessingException e) {
            return buildJSON(ErrorCode.JSON_PARSE_ERROR);
        }
    }

    public String buildJSON(List<Book> bookList) {
        try {
            List<Book> result = new ArrayList<>();
            StringBuilder builder = new StringBuilder("[");
            for (Book book : bookList) {
                builder.append(mapper.writeValueAsString(book));
                builder.append(",");
            }
            builder.deleteCharAt(builder.length() - 1);
            builder.append("]");
            return builder.toString();
        } catch (JsonProcessingException e ) {
            return buildJSON(ErrorCode.JSON_PARSE_ERROR);
        }
    }

    public String buildJSON(ErrorCode errorCode) {
        return new JSONObject()
                .appendField("message", errorsList.getOrDefault(errorCode, "Unspecified error"))
                .toJSONString();
    }
}
