package com.example.jooq_demo.service;

import com.example.jooq_demo.dto.request.AuthorCreationRequest;
import com.example.jooq_demo.dto.request.AuthorUpdateRequest;
import com.example.jooq_demo.dto.request.PageRequest;
import com.example.jooq_demo.dto.response.AuthorResponse;
import com.example.jooq_demo.dto.response.PageResponse;

import java.util.List;

public interface AuthorService {
    void create(AuthorCreationRequest authorCreationRequest);
    void update(Integer id, AuthorUpdateRequest authorUpdateRequest);
    PageResponse<AuthorResponse> findAll(PageRequest pageRequest);
    AuthorResponse findById(Integer id);
    void deleteById(Integer id);
}
