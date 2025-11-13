package com.Alex.database3.mappers.impl;

import com.Alex.database3.domain.dto.BookDto;
import com.Alex.database3.domain.entities.BookEntity;
import com.Alex.database3.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
@Component
public class BookMapperImpl implements Mapper<BookEntity, BookDto> {


    private ModelMapper modelMapper = new ModelMapper();

    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDto mapTo(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookEntity mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }
}
