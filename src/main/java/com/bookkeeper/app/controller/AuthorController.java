package com.bookkeeper.app.controller;

import com.bookkeeper.app.entity.Author;
import com.bookkeeper.app.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        return ResponseEntity.ok(author);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Author> getAuthorByEmail(@PathVariable String email) {
        Author author = authorService.getAuthorByEmail(email);
        return ResponseEntity.ok(author);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Author>> searchByName(@RequestParam String name) {
        List<Author> authors = authorService.searchByName(name);
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Author>> getActiveAuthors() {
        List<Author> authors = authorService.getActiveAuthors();
        return ResponseEntity.ok(authors);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@Valid @RequestBody Author author) {
        Author createdAuthor = authorService.createAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(
            @PathVariable Long id,
            @Valid @RequestBody Author authorDetails) {
        Author updatedAuthor = authorService.updateAuthor(id, authorDetails);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
