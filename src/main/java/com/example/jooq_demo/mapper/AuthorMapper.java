package com.example.jooq_demo.mapper;

import com.example.jooq_demo.dto.request.AuthorCreationRequest;
import com.example.jooq_demo.dto.request.AuthorUpdateRequest;
import com.example.jooq_demo.dto.response.AuthorResponse;
import com.example.jooq_demo.model.tables.pojos.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(AuthorCreationRequest authorCreationRequest);
    Author toAuthor(AuthorUpdateRequest authorUpdateRequest);
    AuthorResponse toAuthorResponse(Author author);
    List<AuthorResponse> toAuthorResponses(List<Author> authors);
}
