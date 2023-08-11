package com.azarkin.librarytesttask.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.azarkin.librarytesttask.entity.Author}
 */
@Value
public class AuthorDto implements Serializable {
    Long id;
    String name;
}