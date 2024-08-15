package com.example.jooq_demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorUpdateRequest {
    private String    firstName;
    private String    lastName;
    private LocalDate dateOfBirth;
}
