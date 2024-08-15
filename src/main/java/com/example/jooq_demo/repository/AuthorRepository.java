package com.example.jooq_demo.repository;

import com.example.jooq_demo.dto.request.AuthorCreationRequest;
import com.example.jooq_demo.dto.request.AuthorUpdateRequest;
import com.example.jooq_demo.dto.request.PageRequest;
import com.example.jooq_demo.dto.response.AuthorResponse;
import com.example.jooq_demo.dto.response.PageResponse;
import com.example.jooq_demo.model.Tables;
import com.example.jooq_demo.model.tables.pojos.Author;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;


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
    public List<AuthorResponse> findAll(){
         List<AuthorResponse> authorResponses = dslContext.select()
                .from(Tables.AUTHOR)
                 .fetchInto(AuthorResponse.class);
         return authorResponses;
    }
    public PageResponse<AuthorResponse> findAll(PageRequest pageRequest){
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
        List<AuthorResponse> authorResponses = dslContext.select()
                .from(Tables.AUTHOR)
                .offset(offset)
                .limit(size)
                .fetchInto(AuthorResponse.class);
        return PageResponse.<AuthorResponse>builder()
                .page(page)
                .size(size)
                .totalPage(totalPage)
                .data(authorResponses)
                .build();
    }
    public AuthorResponse findById(Integer id){
        return dslContext.select()
                .from(Tables.AUTHOR)
                .where(Tables.AUTHOR.ID.equal(id))
                .fetchOneInto(AuthorResponse.class);
    }
}
