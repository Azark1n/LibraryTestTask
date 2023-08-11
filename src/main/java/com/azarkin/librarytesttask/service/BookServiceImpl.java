package com.azarkin.librarytesttask.service;

import com.azarkin.librarytesttask.dto.BookDto;
import com.azarkin.librarytesttask.dto.NewBookDto;
import com.azarkin.librarytesttask.entity.Author;
import com.azarkin.librarytesttask.entity.Book;
import com.azarkin.librarytesttask.exception.NotFoundException;
import com.azarkin.librarytesttask.mapper.BookMapper;
import com.azarkin.librarytesttask.repository.AuthorRepository;
import com.azarkin.librarytesttask.repository.BookRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final BookMapper mapper;

    @Override
    public BookDto create(NewBookDto newBookDto) {
        log.info("Create new book: {}", newBookDto);

        Author author = authorRepository.findById(newBookDto.getAuthorId()).orElseThrow(
                () -> new NotFoundException(String.format("Author not found. authorId = %d", newBookDto.getAuthorId())));

        Book book = mapper.toEntity(newBookDto, author);
        return mapper.toDto(repository.save(book));
    }

    @Override
    public BookDto patch(Long bookId, NewBookDto patch) {
        log.info("Patch book with id = {}, patch data: {}", bookId, patch);

        Book book = repository.findById(bookId).orElseThrow(
                () -> new NotFoundException(String.format("Book not found. bookId = %d", bookId)));

        if (patch.getAuthorId() != null) {
            Author author = authorRepository.findById(patch.getAuthorId()).orElseThrow(
                    () -> new NotFoundException(String.format("Author not found. authorId = %d", patch.getAuthorId())));
            book.setAuthor(author);
        }

        Book patched = repository.save(mapper.partialUpdate(patch, book));

        return mapper.toDto(patched);
    }

    @Override
    public void deleteById(Long bookId) {
        log.info("Delete book with id = {}", bookId);

        repository.deleteById(bookId);
    }

    @Override
    public BookDto getById(Long bookId) {
        log.info("Get book by id = {}", bookId);

        Book book = repository.findById(bookId).orElseThrow(
                () -> new NotFoundException(String.format("Book not found. bookId = %d", bookId)));

        return mapper.toDto(book);
    }

    @Override
    public List<BookDto> getList(Integer page, Integer size, String text, Long isbn, Long authorId) {
        log.info("Get list of book by param: page={}, size={}, text={}, isbn={}, authorId={}", page, size, text, isbn, authorId);

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("name"));

        List<Book> result = repository.findAll(((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (text != null && !text.isBlank()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + text.toLowerCase() + "%"));
            }
            if (isbn != null) {
                predicates.add(criteriaBuilder.equal(root.get("isbn"), isbn));
            }
            if (authorId != null) {
                predicates.add(criteriaBuilder.equal(root.get("authorId"), authorId));
            }
            return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        }), pageRequest).toList();

        return result.stream().map(mapper::toDto).collect(Collectors.toList());
    }


}
