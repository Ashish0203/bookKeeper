package com.bookkeeper.app.repository;

import com.bookkeeper.app.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByAuthor(String author);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByPublisher(String publisher);

    List<Book> findByAvailableTrue();

    @Query("SELECT b FROM Book b WHERE b.price BETWEEN :minPrice AND :maxPrice")
    List<Book> findByPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

    @Query("SELECT b FROM Book b WHERE b.publicationYear BETWEEN :minYear AND :maxYear")
    List<Book> findByPublicationYearRange(@Param("minYear") Integer minYear, @Param("maxYear") Integer maxYear);

    boolean existsByIsbn(String isbn);
}
