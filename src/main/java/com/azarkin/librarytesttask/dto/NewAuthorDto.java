package com.azarkin.librarytesttask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

/**
 * DTO for {@link com.azarkin.librarytesttask.entity.Author}
 */
@Builder
@Jacksonized
@Value
public class NewAuthorDto implements Serializable {
    @NotNull
    @Size(min = 3, max = 255)
    @NotBlank
    String name;
}