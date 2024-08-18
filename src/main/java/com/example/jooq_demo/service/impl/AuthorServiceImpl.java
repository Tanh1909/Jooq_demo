package com.example.jooq_demo.service.impl;

import com.example.jooq_demo.dto.request.AuthorCreationRequest;
import com.example.jooq_demo.dto.request.AuthorUpdateRequest;
import com.example.jooq_demo.dto.request.PageRequest;
import com.example.jooq_demo.dto.response.AuthorResponse;
import com.example.jooq_demo.dto.response.PageResponse;
import com.example.jooq_demo.mapper.AuthorMapper;
import com.example.jooq_demo.model.tables.pojos.Author;
import com.example.jooq_demo.model.tables.pojos.Book;
import com.example.jooq_demo.repository.AuthorRepository;
import com.example.jooq_demo.repository.BookRepository;
import com.example.jooq_demo.service.AuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Transactional
public class AuthorServiceImpl implements AuthorService {
    AuthorRepository authorRepository;
    BookRepository bookRepository;
    AuthorMapper authorMapper;

    @Override
    public void create(AuthorCreationRequest authorCreationRequest) {
        authorRepository.create(authorMapper.toAuthor(authorCreationRequest));
    }

    @Override
    public Map<Author, List<Book>> findBookByAuthorId(Integer id) {
        return authorRepository.findByAuthorId(id);
    }

    @Override
    public void update(Integer id, AuthorUpdateRequest authorUpdateRequest) {
        authorRepository.update(id,authorMapper.toAuthor(authorUpdateRequest));
    }

    @Override
    public PageResponse<AuthorResponse> findAll(PageRequest pageRequest) {
            PageResponse<Author> pageResponse=authorRepository.findAll(pageRequest);

        return PageResponse.<AuthorResponse>builder()
                .size(pageResponse.getSize())
                .totalPage(pageResponse.getTotalPage())
                .page(pageResponse.getPage())
                .data(authorMapper.toAuthorResponses(pageResponse.getData()))
                .build();
    }

    @Override
    public AuthorResponse findById(Integer id) {
        return authorRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
            bookRepository.deleteByAuthorId(id);
            authorRepository.deleteById(id);
    }
}
