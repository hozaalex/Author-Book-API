package com.Alex.database3.controllers;


import com.Alex.database3.TestDataUtil;
import com.Alex.database3.domain.dto.AuthorDto;
import com.Alex.database3.domain.entities.AuthorEntity;
import com.Alex.database3.services.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper ;
    private AuthorService authorService ;

    @Autowired
    public AuthorControllerIntegrationTest(MockMvc mockMvc,AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.authorService = authorService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Create() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(testAuthorA.getName())
        ).andExpect(

          MockMvcResultMatchers.jsonPath("$.age").value(testAuthorA.getAge())
        );


    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testThatListAuthorsReturnsHttpStatus200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/authors")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }


    @Test
    @WithMockUser(roles = "ADMIN")
    public void testThatListAuthorsReturnsListOfAuthors() throws Exception {


        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthor = authorService.save(testAuthorA);


        mockMvc.perform(MockMvcRequestBuilders.get("/authors")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].id").value(savedAuthor.getId())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].name").value("Abigail Rose")
        ).andExpect(

                MockMvcResultMatchers.jsonPath("$.content[0].age").value(60)
        );

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testThatGetAuthorReturnsHttpStatus200WhenAuthorExists() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        authorService.save(testAuthorA);
        mockMvc.perform(

                MockMvcRequestBuilders.get("/authors/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testThatGetAuthorReturnsHttpStatus404WhenNoAuthorExists() throws Exception {

        mockMvc.perform(

                MockMvcRequestBuilders.get("/authors/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testThatGetAuthorReturnsAuthorWhenAuthorExists() throws Exception {

        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        authorService.save(testAuthorA);
        mockMvc.perform(

                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Abigail Rose")
        ).andExpect(

                MockMvcResultMatchers.jsonPath("$.age").value(60)
        );
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testThatFullUpdateAuthorReturnsHttpStatus200WhenAuthorExists() throws Exception {

        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthor = authorService.save(testAuthorA);

        AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDtoA();

        String authorJson = objectMapper.writeValueAsString(testAuthorDtoA);
        mockMvc.perform(

                MockMvcRequestBuilders.get("/authors/"+savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testThatFullUpdateAuthorReturnsHttpStatus404WhenNoAuthorExists() throws Exception {
        AuthorDto testAuthorA = TestDataUtil.createTestAuthorDtoA();
        String authorJson = objectMapper.writeValueAsString(testAuthorA);
        mockMvc.perform(

                MockMvcRequestBuilders.put("/authors/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testThatFullUpdateAuthorUpdatesExistingAuthor() throws Exception {

        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthor = authorService.save(testAuthorA);

        AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDtoA();

        String authorJson = objectMapper.writeValueAsString(testAuthorDtoA);
        mockMvc.perform(

                MockMvcRequestBuilders.get("/authors/"+savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(testAuthorDtoA.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(testAuthorDtoA.getAge())
        );
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testThatPartialUpdateExistingAuthorReturnsHttpStatus200k() throws Exception {

        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthor = authorService.save(testAuthorA);

        AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDtoA();
        testAuthorDtoA.setName("UPDATED");
        String authorJson = objectMapper.writeValueAsString(testAuthorDtoA);
        mockMvc.perform(

                MockMvcRequestBuilders.patch("/authors/"+savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    public void testThatPartialUpdateExistingAuthorReturnsUpdatedAuthor() throws Exception {

        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthor = authorService.save(testAuthorA);

        AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDtoA();
        testAuthorDtoA.setName("UPDATED");
        String authorJson = objectMapper.writeValueAsString(testAuthorDtoA);
        mockMvc.perform(

                MockMvcRequestBuilders.patch("/authors/"+savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(testAuthorDtoA.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(testAuthorDtoA.getAge())
        );
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testThatDeleteAuthorReturnsHttpStatus204ExistingAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthor = authorService.save(testAuthorA);


        mockMvc.perform(

                MockMvcRequestBuilders.delete("/authors/"+savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
