package com.example.jooq_demo.controller;

import com.example.jooq_demo.dto.request.BookCreationRequest;
import com.example.jooq_demo.dto.response.BookResponse;
import com.example.jooq_demo.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequestMapping("/books")
public class BookController {
    BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody BookCreationRequest bookCreationRequest){
        bookService.create(bookCreationRequest);
        return "success";
    }

//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public String update(@PathVariable Integer id,@RequestBody BookCreationRequest bookCreationRequest){
//        bookService.update(id,bookCreationRequest);
//        return "success";
//    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> findAll(){
        return bookService.findAll();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse findById(@PathVariable Integer id){
        return bookService.findById(id);
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteById(@PathVariable Integer id){
        bookService.deleteById(id);
        return "success";
    }
}
