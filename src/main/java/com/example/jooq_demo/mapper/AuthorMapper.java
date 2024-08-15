package com.example.jooq_demo.mapper;

import com.example.jooq_demo.dto.request.AuthorCreationRequest;
import com.example.jooq_demo.dto.request.AuthorUpdateRequest;
import com.example.jooq_demo.dto.response.AuthorResponse;
import com.example.jooq_demo.model.tables.pojos.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(AuthorCreationRequest authorCreationRequest);
    Author toAuthor(AuthorUpdateRequest authorUpdateRequest);
}
