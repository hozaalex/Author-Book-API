package com.Alex.database3.services.impl;

import com.Alex.database3.domain.dto.AuthorDto;
import com.Alex.database3.domain.entities.AuthorEntity;
import com.Alex.database3.repositories.AuthorRepository;
import com.Alex.database3.services.AuthorService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service

public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;


    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    @Override
    public List<AuthorEntity> findAll() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public AuthorEntity partialUpdate(Long id, AuthorEntity updatedAuthor) {
        updatedAuthor.setId(id);

        return authorRepository.findById(id).map(existingAuthor -> {
            Optional.ofNullable(updatedAuthor.getName()).ifPresent(existingAuthor::setName);
            Optional.ofNullable(updatedAuthor.getAge()).ifPresent(existingAuthor::setAge);
            return authorRepository.save(existingAuthor);
        }).orElseThrow(() -> new RuntimeException("Could not find author with id: " + id));
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Page<AuthorEntity> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }
}
