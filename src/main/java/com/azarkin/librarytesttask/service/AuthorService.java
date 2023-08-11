package com.azarkin.librarytesttask.service;

import com.azarkin.librarytesttask.dto.AuthorDto;
import com.azarkin.librarytesttask.dto.NewAuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorDto create(NewAuthorDto newAuthorDto);

    AuthorDto patch(Long authorId, NewAuthorDto patch);

    void deleteById(Long authorId);

    AuthorDto getById(Long authorId);

    List<AuthorDto> getList(Integer page, Integer size, String text);
}
