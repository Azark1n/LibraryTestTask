package com.azarkin.librarytesttask.controller;

import com.azarkin.librarytesttask.dto.BookDto;
import com.azarkin.librarytesttask.dto.NewBookDto;
import com.azarkin.librarytesttask.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Книги", description = "API для работы с книгами")
@RequiredArgsConstructor
@RequestMapping("/book")
@RestController
public class BookController {
    private final BookService service;

    @Operation(summary = "Добавление новой книги")
    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody @Valid NewBookDto newBookDto) {
        BookDto result = service.create(newBookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(summary = "Изменение книги")
    @PatchMapping("/{bookId}")
    public ResponseEntity<BookDto> patch(@PathVariable Long bookId, @RequestBody NewBookDto patch) {
        BookDto result = service.patch(bookId, patch);

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Удаление книги")
    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long bookId) {
        service.deleteById(bookId);
    }

    @Operation(summary = "Получение информации о книги по идентификатору")
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> getById(@PathVariable Long bookId) {
        BookDto result = service.getById(bookId);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получение списка книг")
    @GetMapping
    public ResponseEntity<List<BookDto>> getList(@RequestParam(required = false) String text,
                                                   @RequestParam(required = false) Long isbn,
                                                   @RequestParam(required = false) Long authorId,
                                                   @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(name = "size", defaultValue = "10") Integer size) {
        List<BookDto> result = service.getList(page, size, text, isbn, authorId);
        return ResponseEntity.ok(result);
    }
}
