package com.bookkeeper.app.service;

import com.bookkeeper.app.entity.Author;
import com.bookkeeper.app.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
    }

    public Author getAuthorByEmail(String email) {
        return authorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Author not found with email: " + email));
    }

    public Author createAuthor(Author author) {
        if (authorRepository.existsByEmail(author.getEmail())) {
            throw new RuntimeException("Author with email " + author.getEmail() + " already exists");
        }
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = getAuthorById(id);

        author.setName(authorDetails.getName());
        author.setEmail(authorDetails.getEmail());
        author.setBio(authorDetails.getBio());
        author.setActive(authorDetails.getActive());

        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }

    public List<Author> searchByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Author> getActiveAuthors() {
        return authorRepository.findByActiveTrue();
    }
}
