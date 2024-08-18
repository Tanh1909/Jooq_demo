package com.example.jooq_demo.repository;

import com.example.jooq_demo.dto.request.PageRequest;
import com.example.jooq_demo.dto.response.AuthorResponse;
import com.example.jooq_demo.dto.response.BookResponse;
import com.example.jooq_demo.dto.response.PageResponse;
import com.example.jooq_demo.model.Tables;
import com.example.jooq_demo.model.tables.pojos.Author;
import com.example.jooq_demo.model.tables.pojos.Book;
import com.example.jooq_demo.model.tables.records.AuthorRecord;
import com.example.jooq_demo.model.tables.records.BookRecord;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthorRepository {
    DSLContext dslContext;
    public void create(Author author){
        dslContext.insertInto(Tables.AUTHOR)
                .set(Tables.AUTHOR.LAST_NAME,author.getLastName())
                .set(Tables.AUTHOR.FIRST_NAME,author.getFirstName())
                .set(Tables.AUTHOR.DATE_OF_BIRTH,author.getDateOfBirth())
                .execute();
    }
    public void update(Integer id,Author author){
        dslContext.update(Tables.AUTHOR)
                .set(Tables.AUTHOR.LAST_NAME,author.getLastName())
                .set(Tables.AUTHOR.FIRST_NAME,author.getFirstName())
                .set(Tables.AUTHOR.DATE_OF_BIRTH,author.getDateOfBirth())
                .where(Tables.AUTHOR.ID.eq(id))
                .execute();
    }
    public void deleteById(Integer id){
            dslContext.delete(Tables.AUTHOR)
                    .where(Tables.AUTHOR.ID.eq(id))
                    .execute();
    }

    public PageResponse<Author> findAll(PageRequest pageRequest){
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
        List<Author> authors = dslContext.select()
                .from(Tables.AUTHOR)
                .offset(offset)
                .limit(size)
                .fetchInto(Author.class);
        return PageResponse.<Author>builder()
                .page(page)
                .size(size)
                .totalPage(totalPage)
                .data(authors)
                .build();
    }
    public AuthorResponse findById(Integer id){
        return dslContext.select()
                .from(Tables.AUTHOR)
                .where(Tables.AUTHOR.ID.equal(id))
                .fetchOneInto(AuthorResponse.class);
    }
    public Map<Author,List<Book>> findByAuthorId(Integer id){
     var data =  dslContext.select()
                .from(Tables.AUTHOR)
                .innerJoin(Tables.BOOK)
                .on(Tables.AUTHOR.ID.eq(Tables.BOOK.AUTHOR_ID))
                .where(Tables.AUTHOR.ID.eq(id))
              .fetchGroups(Tables.AUTHOR,Tables.BOOK);
            Map<Author,List<Book>> response=new HashMap<>();
        for (Map.Entry<AuthorRecord, Result<BookRecord>> entry: data.entrySet()) {
                Author author=entry.getKey().into(Author.class);
                List<Book> books=entry.getValue()
                        .stream().map(bookRecord -> bookRecord.into(Book.class)).collect(Collectors.toList());
                response.put(author,books);
        }
        return response;
    }
}
