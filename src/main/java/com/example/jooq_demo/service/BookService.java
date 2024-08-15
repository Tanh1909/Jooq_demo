package com.example.jooq_demo.service;

import com.example.jooq_demo.dto.request.BookCreationRequest;
import com.example.jooq_demo.dto.response.BookResponse;

import java.util.List;

public interface BookService {
    void create(BookCreationRequest bookCreationRequest);
    void deleteById(Integer id);
    BookResponse findById(Integer id);
    List<BookResponse> findAll();

}
