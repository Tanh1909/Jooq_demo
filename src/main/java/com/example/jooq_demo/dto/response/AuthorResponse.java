package com.example.jooq_demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorResponse {
    private Integer id;
    private String    firstName;
    private String    lastName;
    private LocalDate dateOfBirth;
    private List<BookResponse> bookResponses;
}
