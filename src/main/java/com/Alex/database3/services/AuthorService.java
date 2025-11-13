package com.Alex.database3.services;

import com.Alex.database3.domain.dto.AuthorDto;
import com.Alex.database3.domain.entities.AuthorEntity;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

    AuthorEntity save(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();


    Optional<AuthorEntity> findOne(Long id);

    boolean isExists(Long id);

    AuthorEntity partialUpdate(Long id, AuthorEntity updatedAuthor) ;

    void delete(Long id);

    Page<AuthorEntity> findAll (Pageable pageable);
}
