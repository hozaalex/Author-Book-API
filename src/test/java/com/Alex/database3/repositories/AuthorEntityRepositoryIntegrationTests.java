//package com.Alex.database3.repositories;
//
//import com.Alex.database3.TestDataUtil;
//
//import com.Alex.database3.domain.entities.AuthorEntity;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class AuthorEntityRepositoryIntegrationTests {
//
//    private AuthorRepository underTest;
//
//    @Autowired
//    public AuthorEntityRepositoryIntegrationTests(AuthorRepository underTest) {
//        this.underTest = underTest;
//    }
//
//    @Test
//    public void testThatAuthorCanBeCreatedAndRecalled(){
//
//        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
//        underTest.save(authorEntity);
//        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(authorEntity);
//
//    }
//    @Test
//    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
//        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
//        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorB();
//        AuthorEntity authorEntityC = TestDataUtil.createTestAuthorC();
//        underTest.save(authorEntityA);
//        underTest.save(authorEntityB);
//        underTest.save(authorEntityC);
//        Iterable<AuthorEntity> result=underTest.findAll();
//        assertThat(result)
//                .hasSize(3)
//                .containsExactly(authorEntityA, authorEntityB, authorEntityC);;
//
//    }
////    @Test
////    public void testThatAuthorCanBeUpdated(){
////        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
////        underTest.save(authorEntityA);
////        authorEntityA.setName("Susanu");
////        underTest.save(authorEntityA);
////        Optional<AuthorEntity> result=underTest.findById(authorEntityA.getId());
////        assertThat(result).isPresent();
////        assertThat(result.get().getId()).isEqualTo(authorEntityA.getId());
////        assertThat(result.get().getName()).isEqualTo("Susanu");
////        assertThat(result.get().getAge()).isEqualTo(authorEntityA.getAge());
////
////    }
//
//    @Test
//    public void testThatAuthorCanBeDeleted(){
//        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
//        underTest.save(authorEntityA);
//        underTest.delete(authorEntityA);
//        Optional<AuthorEntity> result=underTest.findById(authorEntityA.getId());
//        assertThat(result).isNotPresent();
//
//    }
//    @Test
//    public void testThatGetAuthorsWithAgeLessThan(){
//        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
//        AuthorEntity testAuthorEntityB = TestDataUtil.createTestAuthorB();
//        AuthorEntity testAuthorEntityC = TestDataUtil.createTestAuthorC();
//        underTest.save(authorEntityA);
//        underTest.save(testAuthorEntityB);
//        underTest.save(testAuthorEntityC);
//        Iterable<AuthorEntity> result= underTest.ageLessThan(50);
//        assertThat(result).hasSize(2);
//        assertThat(result).containsExactly(testAuthorEntityB, testAuthorEntityC);
//    }
//    //@Test
////    public void testThatGetAuthorsWithAgeGreaterThan(){
////        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
////        AuthorEntity testAuthorEntityB = TestDataUtil.createTestAuthorB();
////        AuthorEntity testAuthorEntityC = TestDataUtil.createTestAuthorC();
////        underTest.save(authorEntityA);
////        underTest.save(testAuthorEntityB);
////        underTest.save(testAuthorEntityC);
////
////        Iterable<AuthorEntity> result=underTest.findAuthorsWithAgeGreaterThan(50);
////
////        assertThat(result).containsExactly(authorEntityA);
////
////    }
//}
