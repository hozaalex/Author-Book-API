package com.Alex.database3.controllers;


import com.Alex.database3.domain.dto.AuthorDto;
import com.Alex.database3.domain.entities.AuthorEntity;
import com.Alex.database3.mappers.Mapper;
import com.Alex.database3.services.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;

@RestController


public class AuthorController {

    public AuthorService authorService;
    private Mapper<AuthorEntity, AuthorDto> authorMapper;


    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping(path="/authors")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author) {
        AuthorEntity authorEntity= authorMapper.mapFrom(author);
        AuthorEntity saved= authorService.save(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(saved), HttpStatus.CREATED);
    }

    @GetMapping(path="/authors")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<AuthorDto> listAuthors(Pageable pageable) {

        Page<AuthorEntity> authors=authorService.findAll(pageable);
        return authors.map(authorMapper::mapTo);
    }

    @GetMapping(path="/authors/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long id) {
        Optional<AuthorEntity> foundAuthor= authorService.findOne(id);
        return foundAuthor.map(authorEntity -> {
            AuthorDto authorDto = authorMapper.mapTo(authorEntity);
            return new ResponseEntity<>(authorDto, HttpStatus.OK);

        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping(path="/authors/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(@PathVariable("id") Long id,@RequestBody AuthorDto authorDto) {
        if(!authorService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorDto.setId(id);
        AuthorEntity authorEntity= authorMapper.mapFrom(authorDto);
        AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.OK);
    }

    @PatchMapping(path="/authors/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<AuthorDto> partialUpdate(@PathVariable("id") Long id,@RequestBody AuthorDto authorDto) {

        if(!authorService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity updatedAuthor = authorService.partialUpdate(id,authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(updatedAuthor),HttpStatus.OK);
    }

    @DeleteMapping(path="/authors/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthorDto> deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
