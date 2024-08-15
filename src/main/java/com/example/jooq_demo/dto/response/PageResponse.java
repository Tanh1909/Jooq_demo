package com.example.jooq_demo.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {
    private List<T> data;
    private Integer page=0;
    private Integer size=5;
    private Integer totalPage;
}
