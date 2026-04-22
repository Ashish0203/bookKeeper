package com.bookkeeper.app.controller;

import com.bookkeeper.app.entity.Book;
import com.bookkeeper.app.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        Book book = bookService.getBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/search/author")
    public ResponseEntity<List<Book>> searchByAuthor(@RequestParam String author) {
        List<Book> books = bookService.searchByAuthor(author);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam String title) {
        List<Book> books = bookService.searchByTitle(title);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/publisher")
    public ResponseEntity<List<Book>> searchByPublisher(@RequestParam String publisher) {
        List<Book> books = bookService.searchByPublisher(publisher);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        List<Book> books = bookService.getAvailableBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/price")
    public ResponseEntity<List<Book>> searchByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        List<Book> books = bookService.searchByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/year")
    public ResponseEntity<List<Book>> searchByPublicationYearRange(
            @RequestParam Integer minYear,
            @RequestParam Integer maxYear) {
        List<Book> books = bookService.searchByPublicationYearRange(minYear, maxYear);
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails);
        return ResponseEntity.ok(updatedBook);
    }

    @PatchMapping("/{id}/quantity")
    public ResponseEntity<Book> updateQuantity(
            @PathVariable Long id,
            @RequestParam Integer quantity) {
        Book updatedBook = bookService.updateQuantity(id, quantity);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
