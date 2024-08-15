package com.example.jooq_demo.service.impl;

import com.example.jooq_demo.dto.request.BookCreationRequest;
import com.example.jooq_demo.dto.response.BookResponse;
import com.example.jooq_demo.mapper.BookMapper;
import com.example.jooq_demo.repository.AuthorRepository;
import com.example.jooq_demo.repository.BookRepository;
import com.example.jooq_demo.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Transactional
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;
    AuthorRepository authorRepository;
    BookMapper bookMapper;

    @Override
    public void create(BookCreationRequest bookCreationRequest) {
        bookRepository.create(bookMapper.toBook(bookCreationRequest));
    }

    @Override
    public BookResponse findById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<BookResponse> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }
}
