package com.azarkin.librarytesttask.service;

import com.azarkin.librarytesttask.dto.BookDto;
import com.azarkin.librarytesttask.dto.NewBookDto;

import java.util.List;

public interface BookService {
    BookDto create(NewBookDto newBookDto);

    BookDto patch(Long bookId, NewBookDto patch);

    void deleteById(Long bookId);

    BookDto getById(Long bookId);

    List<BookDto> getList(Integer page, Integer size, String text, Long isbn, Long authorId);
}
