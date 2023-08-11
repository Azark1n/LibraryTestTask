package com.azarkin.librarytesttask.repository;

import com.azarkin.librarytesttask.entity.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByNameLikeIgnoreCase(String name, Pageable pageable);
}