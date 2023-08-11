package com.azarkin.librarytesttask.controller;

import com.azarkin.librarytesttask.dto.AuthorDto;
import com.azarkin.librarytesttask.dto.NewAuthorDto;
import com.azarkin.librarytesttask.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/author")
@RestController
public class AuthorController {
    private final AuthorService service;

    @Operation(summary = "Добавление нового автора")
    @PostMapping
    public ResponseEntity<AuthorDto> create(@RequestBody @Valid NewAuthorDto newAuthorDto) {
        AuthorDto result = service.create(newAuthorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(summary = "Изменение автора")
    @PatchMapping("/{authorId}")
    public ResponseEntity<AuthorDto> patch(@PathVariable Long authorId, @RequestBody @Valid NewAuthorDto patch) {
        AuthorDto result = service.patch(authorId, patch);

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Удаление автора")
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long authorId) {
        service.deleteById(authorId);
    }

    @Operation(summary = "Получение информации об авторе по идентификатору")
    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorDto> getById(@PathVariable Long authorId) {
        AuthorDto result = service.getById(authorId);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получение списка авторов")
    @GetMapping
    public ResponseEntity<List<AuthorDto>> getList(@RequestParam(required = false) String text,
                                                   @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(name = "size", defaultValue = "10") Integer size) {
        List<AuthorDto> result = service.getList(page, size, text);
        return ResponseEntity.ok(result);
    }
}
