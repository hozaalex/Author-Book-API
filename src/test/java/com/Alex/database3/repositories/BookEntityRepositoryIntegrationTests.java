package com.Alex.database3.repositories;

import com.Alex.database3.TestDataUtil;

import com.Alex.database3.domain.entities.AuthorEntity;
import com.Alex.database3.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)

public class BookEntityRepositoryIntegrationTests {


    private BookRepository underTest;
    private AuthorRepository authorRepository;

    @Autowired
    public BookEntityRepositoryIntegrationTests(BookRepository underTest, AuthorRepository authorRepository) {

        this.underTest = underTest;
        this.authorRepository = authorRepository;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {

        AuthorEntity authorEntity =TestDataUtil.createTestAuthorA();
        authorRepository.save(authorEntity);
        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(authorEntity);
        underTest.save(bookEntity);
        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        assertThat(result.isPresent());
        assertThat(result.get()).isEqualTo(bookEntity);

    }

    @Test
    public void testThatMultipleBookCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity =TestDataUtil.createTestAuthorA();
        authorRepository.save(authorEntity);
        BookEntity bookEntity1 = TestDataUtil.createTestBookEntityA(authorEntity);
        underTest.save(bookEntity1);
        BookEntity bookEntity2 = TestDataUtil.createTestBookB(authorEntity);
        underTest.save(bookEntity2);
        BookEntity bookEntity3 = TestDataUtil.createTestBookC(authorEntity);
        underTest.save(bookEntity3);
        Iterable<BookEntity> result=underTest.findAll();
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(bookEntity1, bookEntity2, bookEntity3);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        AuthorEntity authorEntity =TestDataUtil.createTestAuthorA();
        authorRepository.save(authorEntity);

        BookEntity bookEntity1 = TestDataUtil.createTestBookEntityA(authorEntity);

        underTest.save(bookEntity1);
        bookEntity1.setTitle("UPDATED");
        underTest.save(bookEntity1);
        Optional<BookEntity> result = underTest.findById(bookEntity1.getIsbn());
        assertThat(result.isPresent());
        assertThat(result.get()).isEqualTo(bookEntity1);

    }

    @Test
    public void testThatBookCanBeDeleted() {
        AuthorEntity authorEntity =TestDataUtil.createTestAuthorA();
        authorRepository.save(authorEntity);
        BookEntity bookEntity1 = TestDataUtil.createTestBookEntityA(authorEntity);
        underTest.save(bookEntity1);
        underTest.delete(bookEntity1);
        Optional<BookEntity> result = underTest.findById(bookEntity1.getIsbn());
        assertThat(result.isEmpty());
    }


}
