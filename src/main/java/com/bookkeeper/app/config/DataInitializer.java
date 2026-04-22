package com.bookkeeper.app.config;

import com.bookkeeper.app.entity.Book;
import com.bookkeeper.app.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Override
    public void run(String... args) {
        if (bookRepository.count() == 0) {
            log.info("Initializing database with sample books...");
            
            bookRepository.save(Book.builder()
                    .title("The Great Gatsby")
                    .author("F. Scott Fitzgerald")
                    .isbn("978-0743273565")
                    .publisher("Scribner")
                    .publicationYear(1925)
                    .price(new BigDecimal("12.99"))
                    .quantity(50)
                    .description("A classic American novel set in the Jazz Age")
                    .available(true)
                    .build());

            bookRepository.save(Book.builder()
                    .title("To Kill a Mockingbird")
                    .author("Harper Lee")
                    .isbn("978-0061120084")
                    .publisher("Harper Perennial")
                    .publicationYear(1960)
                    .price(new BigDecimal("14.99"))
                    .quantity(45)
                    .description("A gripping tale of racial injustice and childhood innocence")
                    .available(true)
                    .build());

            bookRepository.save(Book.builder()
                    .title("1984")
                    .author("George Orwell")
                    .isbn("978-0451524935")
                    .publisher("Signet Classic")
                    .publicationYear(1949)
                    .price(new BigDecimal("9.99"))
                    .quantity(60)
                    .description("A dystopian social science fiction novel")
                    .available(true)
                    .build());

            bookRepository.save(Book.builder()
                    .title("Pride and Prejudice")
                    .author("Jane Austen")
                    .isbn("978-0141439518")
                    .publisher("Penguin Classics")
                    .publicationYear(1813)
                    .price(new BigDecimal("11.99"))
                    .quantity(40)
                    .description("A romantic novel of manners")
                    .available(true)
                    .build());

            bookRepository.save(Book.builder()
                    .title("The Catcher in the Rye")
                    .author("J.D. Salinger")
                    .isbn("978-0316769488")
                    .publisher("Little, Brown and Company")
                    .publicationYear(1951)
                    .price(new BigDecimal("13.99"))
                    .quantity(35)
                    .description("A story of teenage alienation and loss")
                    .available(true)
                    .build());

            log.info("Successfully added {} sample books to the database", bookRepository.count());
        } else {
            log.info("Database already contains {} books. Skipping initialization.", bookRepository.count());
        }
    }
}
