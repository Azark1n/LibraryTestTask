package com.azarkin.librarytesttask.mapper;

import com.azarkin.librarytesttask.dto.AuthorDto;
import com.azarkin.librarytesttask.dto.NewAuthorDto;
import com.azarkin.librarytesttask.entity.Author;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper {
    Author toEntity(NewAuthorDto newAuthorDto);

    AuthorDto toDto(Author author);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Author partialUpdate(NewAuthorDto newAuthorDto, @MappingTarget Author author);
}