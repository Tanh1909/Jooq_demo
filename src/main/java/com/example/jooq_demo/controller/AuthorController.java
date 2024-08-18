package com.example.jooq_demo.controller;

import com.example.jooq_demo.dto.request.AuthorCreationRequest;
import com.example.jooq_demo.dto.request.AuthorUpdateRequest;
import com.example.jooq_demo.dto.request.PageRequest;
import com.example.jooq_demo.dto.response.AuthorResponse;
import com.example.jooq_demo.dto.response.PageResponse;
import com.example.jooq_demo.model.tables.pojos.Author;
import com.example.jooq_demo.model.tables.pojos.Book;
import com.example.jooq_demo.service.AuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/authors")
public class AuthorController {
    AuthorService authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody AuthorCreationRequest authorCreationRequest){
        authorService.create(authorCreationRequest);
        return "success";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String update(@PathVariable Integer id,@RequestBody AuthorUpdateRequest authorUpdateRequest){
        authorService.update(id,authorUpdateRequest);
        return "success";
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<AuthorResponse> findAll(@ModelAttribute PageRequest pageRequest){
        return authorService.findAll(pageRequest);
    }



    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorResponse findById(@PathVariable Integer id){
        return authorService.findById(id);
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteById(@PathVariable Integer id){
         authorService.deleteById(id);
         return "success";
    }



    @GetMapping("/{id}/books")
    @ResponseStatus(HttpStatus.OK)
    public Map<Author,List<Book>> findBookByAuthorId(@PathVariable Integer id){
         return authorService.findBookByAuthorId(id);
    }



}
