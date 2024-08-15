package com.example.jooq_demo.mapper;

import com.example.jooq_demo.dto.request.BookCreationRequest;
import com.example.jooq_demo.model.tables.pojos.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toBook(BookCreationRequest bookCreationRequest);
}
