package com.azarkin.librarytesttask.mapper;

import com.azarkin.librarytesttask.dto.BookDto;
import com.azarkin.librarytesttask.dto.NewBookDto;
import com.azarkin.librarytesttask.entity.Author;
import com.azarkin.librarytesttask.entity.Book;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {AuthorMapper.class})
public interface BookMapper {
    @Mapping(target = "name", source = "newBookDto.name")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "id", expression = "java(null)")
    Book toEntity(NewBookDto newBookDto, Author author);

    BookDto toDto(Book book);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Book partialUpdate(NewBookDto newBookDto, @MappingTarget Book book);
}