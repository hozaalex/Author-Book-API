package com.Alex.database3.repositories;


import com.Alex.database3.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, String> {


}
