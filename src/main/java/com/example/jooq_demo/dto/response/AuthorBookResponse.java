package com.example.jooq_demo.dto.response;

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
public class AuthorBookResponse {
    private Integer id;
    private String    firstName;
    private String    lastName;
    private LocalDate dateOfBirth;
}
