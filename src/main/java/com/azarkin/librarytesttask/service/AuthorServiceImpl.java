package com.azarkin.librarytesttask.service;

import com.azarkin.librarytesttask.dto.AuthorDto;
import com.azarkin.librarytesttask.dto.NewAuthorDto;
import com.azarkin.librarytesttask.entity.Author;
import com.azarkin.librarytesttask.exception.NotFoundException;
import com.azarkin.librarytesttask.mapper.AuthorMapper;
import com.azarkin.librarytesttask.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;
    private final AuthorMapper mapper;

    @Override
    public AuthorDto create(NewAuthorDto newAuthorDto) {
        log.info("Create new author: {}", newAuthorDto);

        Author author = mapper.toEntity(newAuthorDto);
        return mapper.toDto(repository.save(author));
    }

    @Override
    public AuthorDto patch(Long authorId, NewAuthorDto patch) {
        log.info("Patch author with id = {}, patch data: {}", authorId, patch);

        Author author = repository.findById(authorId).orElseThrow(
                () -> new NotFoundException(String.format("Author not found. authorId = %d", authorId)));

        Author patched = repository.save(mapper.partialUpdate(patch, author));

        return mapper.toDto(patched);
    }

    @Override
    public void deleteById(Long authorId) {
        log.info("Delete author with id = {}", authorId);

        repository.deleteById(authorId);
    }

    @Override
    public AuthorDto getById(Long authorId) {
        log.info("Get author by id = {}", authorId);

        Author author = repository.findById(authorId).orElseThrow(
                () -> new NotFoundException(String.format("Author not found. authorId = %d", authorId)));

        return mapper.toDto(author);
    }

    @Override
    public List<AuthorDto> getList(Integer page, Integer size, String text) {
        log.info("Get list of author by param: page={}, size={}, text={}", page, size, text);

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("name"));

        List<Author> result;

        if (text == null) {
            result = repository.findAll(pageRequest).toList();
        } else {
            result = repository.findByNameLikeIgnoreCase("%" + text + "%", pageRequest);
        }
        return result.stream().map(mapper::toDto).collect(Collectors.toList());
    }


}
