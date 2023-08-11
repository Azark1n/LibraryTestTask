package com.azarkin.librarytesttask.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.azarkin.librarytesttask.entity.Book}
 */
@Value
public class BookDto implements Serializable {
    Long id;

    String name;

    Long isbn;

    AuthorDto author;
}