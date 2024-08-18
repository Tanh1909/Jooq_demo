package com.example.jooq_demo.service;

import com.example.jooq_demo.dto.request.AuthorCreationRequest;
import com.example.jooq_demo.dto.request.AuthorUpdateRequest;
import com.example.jooq_demo.dto.request.PageRequest;
import com.example.jooq_demo.dto.response.AuthorResponse;
import com.example.jooq_demo.dto.response.PageResponse;
import com.example.jooq_demo.model.tables.pojos.Author;
import com.example.jooq_demo.model.tables.pojos.Book;

import java.util.List;
import java.util.Map;

public interface AuthorService {
    void create(AuthorCreationRequest authorCreationRequest);
    void update(Integer id, AuthorUpdateRequest authorUpdateRequest);
    PageResponse<AuthorResponse> findAll(PageRequest pageRequest);
    AuthorResponse findById(Integer id);
    void deleteById(Integer id);
    Map<Author,List<Book>> findBookByAuthorId(Integer id);
}
