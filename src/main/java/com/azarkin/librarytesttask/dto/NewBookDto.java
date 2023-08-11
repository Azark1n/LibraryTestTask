package com.azarkin.librarytesttask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.azarkin.librarytesttask.entity.Book}
 */
@Value
public class NewBookDto implements Serializable {
    @NotNull
    @Size(min = 3, max = 255)
    @NotBlank
    String name;

    @NotNull
    @Positive
    Long isbn;

    @NotNull
    @Positive
    Long authorId;
}