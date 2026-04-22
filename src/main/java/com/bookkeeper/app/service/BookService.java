package com.bookkeeper.app.service;

import com.bookkeeper.app.entity.Book;
import com.bookkeeper.app.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("Book not found with ISBN: " + isbn));
    }

    public Book createBook(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new RuntimeException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublisher(bookDetails.getPublisher());
        book.setPublicationYear(bookDetails.getPublicationYear());
        book.setPrice(bookDetails.getPrice());
        book.setQuantity(bookDetails.getQuantity());
        book.setDescription(bookDetails.getDescription());
        book.setAvailable(bookDetails.getAvailable());

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }

    public List<Book> searchByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> searchByPublisher(String publisher) {
        return bookRepository.findByPublisher(publisher);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailableTrue();
    }

    public List<Book> searchByPriceRange(Double minPrice, Double maxPrice) {
        return bookRepository.findByPriceRange(minPrice, maxPrice);
    }

    public List<Book> searchByPublicationYearRange(Integer minYear, Integer maxYear) {
        return bookRepository.findByPublicationYearRange(minYear, maxYear);
    }

    public Book updateQuantity(Long id, Integer quantity) {
        Book book = getBookById(id);
        book.setQuantity(quantity);
        book.setAvailable(quantity > 0);
        return bookRepository.save(book);
    }
}
