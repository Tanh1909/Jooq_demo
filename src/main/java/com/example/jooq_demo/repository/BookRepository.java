package com.example.jooq_demo.repository;

import com.example.jooq_demo.dto.request.BookCreationRequest;
import com.example.jooq_demo.dto.request.PageRequest;
import com.example.jooq_demo.dto.response.AuthorResponse;
import com.example.jooq_demo.dto.response.BookResponse;
import com.example.jooq_demo.dto.response.PageResponse;
import com.example.jooq_demo.model.Tables;
import com.example.jooq_demo.model.tables.Author;
import com.example.jooq_demo.model.tables.pojos.Book;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class BookRepository {
    DSLContext dslContext;
    public void create(Book book){
        dslContext.insertInto(Tables.BOOK)
                .set(Tables.BOOK.NAME,book.getName())
                .set(Tables.BOOK.PAGE,book.getPage())
                .set(Tables.BOOK.AUTHOR_ID,book.getAuthorId())
                .execute();
    }
    public BookResponse findById(Integer id){
        return dslContext.select()
                .from(Tables.BOOK)
                .where(Tables.BOOK.ID.eq(id))
                .fetchOneInto(BookResponse.class);
    }
    public void deleteById(Integer id){
        dslContext.delete(Tables.BOOK)
                .where(Tables.BOOK.ID.eq(id))
                .execute();
    }
    public void deleteByAuthorId(Integer id){
        dslContext.delete(Tables.BOOK)
                .where(Tables.BOOK.AUTHOR_ID.eq(id))
                .execute();
    }
    public List<BookResponse> findAll(){
        return dslContext.select()
                .from(Tables.BOOK)
                .fetchInto(BookResponse.class);
    }



    public PageResponse<BookResponse> findAll(PageRequest pageRequest){
        int totalRecords=dslContext.selectCount()
                .from(Tables.AUTHOR)
                .fetchOne(0,int.class);
        int page=pageRequest.getPage();
        int size=pageRequest.getSize();
        int totalPage=(int) Math.ceil(totalRecords*1f/ size);
        if(page>totalPage){
            page=1;
        }
        int offset= (page-1)*size;
        List<BookResponse> bookResponses = dslContext.select()
                .from(Tables.BOOK)
                .offset(offset)
                .limit(size)
                .fetchInto(BookResponse.class);
        return PageResponse.<BookResponse>builder()
                .page(page)
                .size(size)
                .totalPage(totalPage)
                .data(bookResponses)
                .build();
    }
    public PageResponse<BookResponse> findBookByAuthorId(Integer authorId,PageRequest pageRequest){
        int totalRecords=dslContext.selectCount()
                .from(Tables.AUTHOR)
                .fetchOne(0,int.class);
        int page=pageRequest.getPage();
        int size=pageRequest.getSize();
        int totalPage=(int) Math.ceil(totalRecords*1f/ size);
        if(page>totalPage){
            page=1;
        }
        int offset= (page-1)*size;
        List<BookResponse> bookResponses = dslContext.select()
                .from(Tables.BOOK)
                .where(Tables.BOOK.AUTHOR_ID.eq(authorId))
                .offset(offset)
                .limit(size)
                .fetchInto(BookResponse.class);
        return PageResponse.<BookResponse>builder()
                .page(page)
                .size(size)
                .totalPage(totalPage)
                .data(bookResponses)
                .build();
    }
}
