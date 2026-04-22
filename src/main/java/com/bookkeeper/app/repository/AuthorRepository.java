package com.bookkeeper.app.repository;

import com.bookkeeper.app.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByEmail(String email);

    List<Author> findByNameContainingIgnoreCase(String name);

    List<Author> findByActiveTrue();

    boolean existsByEmail(String email);
}
