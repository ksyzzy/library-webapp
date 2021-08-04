package com.example.librarybackend.helpers;

import com.example.librarybackend.enums.ErrorCode;
import com.example.librarybackend.models.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
        errorsList.put(ErrorCode.GENERIC_ERROR, "Some generic error has occurred");
        errorsList.put(ErrorCode.ENTITY_DOES_NOT_EXIST, "Specified entity does not exist");
        this.errorsList = errorsList;
    }

    public String buildJSON(Book book) {
        try {
            return this.mapper.writeValueAsString(book);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
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
            if (builder.length() > 1) builder.deleteCharAt(builder.length() - 1);
            builder.append("]");
            return builder.toString();
        } catch (JsonProcessingException e ) {
            e.printStackTrace();
            return buildJSON(ErrorCode.JSON_PARSE_ERROR);
        }
    }

    public String buildJSON(ErrorCode errorCode) {
        return new JSONObject()
                .appendField("message", errorsList.getOrDefault(errorCode, "Unspecified error"))
                .toJSONString();
    }

    public String buildJSON(String value) {
        return new JSONObject()
                .appendField("message", value)
                .toJSONString();
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }
}

