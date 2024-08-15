package com.example.jooq_demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCreationRequest {
    private String  name;
    private Integer page;
    private Integer authorId;
}
