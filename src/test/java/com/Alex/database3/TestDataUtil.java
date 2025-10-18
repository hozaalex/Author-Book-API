package com.Alex.database3;

import com.Alex.database3.domain.dto.AuthorDto;
import com.Alex.database3.domain.dto.BookDto;
import com.Alex.database3.domain.entities.AuthorEntity;
import com.Alex.database3.domain.entities.BookEntity;

public final class TestDataUtil{

    private TestDataUtil(){

    }


    public static AuthorDto createTestAuthorDtoA() {
        return AuthorDto.builder()

                .name("Florin Salam")
                .age(60)
                .build();
    }

    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()

                .name("Florin Salam")
                .age(60)
                .build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()

                .name("Mr. Juve")
                .age(45)
                .build();
    }

    public static AuthorEntity createTestAuthorC() {
        return AuthorEntity.builder()

                .name("Leo Rosiori")
                .age(24)
                .build();
    }


    public static BookEntity createTestBookEntityA(final AuthorEntity authorEntity) {
        return BookEntity.builder().isbn("978-1-2345-6789-0").title("Zombie Dance").author(authorEntity).build();
    }


    public static BookEntity createTestBookB(final AuthorEntity authorEntity) {
        return BookEntity.builder().isbn("978-1-2345-6789-5").title("Jocul Calamarului").author(authorEntity).build();
    }


    public static BookEntity createTestBookC(final AuthorEntity authorEntity) {
        return BookEntity.builder().isbn("978-1-2345-6789-3").title("CREIC").author(authorEntity).build();
    }

    public static BookDto createTestBookDtoA(final AuthorDto author) {
        return BookDto.builder()
                .isbn("978-1-2345-6789-0")
                .title("Zombie Dance")
                .author(author)
                .build();
    }
}
