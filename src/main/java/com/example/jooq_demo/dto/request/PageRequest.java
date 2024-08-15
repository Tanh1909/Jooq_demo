package com.example.jooq_demo.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageRequest {
    private Integer page=1;
    private Integer size=5;
}
