package com.Alex.database3.services.impl;

import com.Alex.database3.domain.entities.BookEntity;
import com.Alex.database3.repositories.BookRepository;
import com.Alex.database3.services.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {


    private BookRepository bookRepository;


    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    
    public BookEntity createBook(String isbn, BookEntity book) {

        book.setIsbn(isbn);
        return bookRepository.save(book);
    }
    @Override
    public List<BookEntity> findAll() {
        return StreamSupport
                .stream(
                        bookRepository.findAll().spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookEntity> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public boolean isExists(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public BookEntity partialUpdate(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);

        return bookRepository.findById(isbn).map(existingBook -> {
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBook::setTitle);
            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Book does not exist"));
    }

    @Override
    public void delete(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
