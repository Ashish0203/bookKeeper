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

            bookRepository.save(Book.builder()
                    .title("Spring Boot in Action")
                    .author("Craig Walls")
                    .isbn("978-1617292545")
                    .publisher("Manning Publications")
                    .publicationYear(2016)
                    .price(new BigDecimal("39.99"))
                    .quantity(30)
                    .description("Comprehensive guide to building applications with Spring Boot")
                    .available(true)
                    .build());

            bookRepository.save(Book.builder()
                    .title("Spring in Action")
                    .author("Craig Walls")
                    .isbn("978-1617297571")
                    .publisher("Manning Publications")
                    .publicationYear(2022)
                    .price(new BigDecimal("49.99"))
                    .quantity(25)
                    .description("Definitive guide to the Spring Framework and modern Spring development")
                    .available(true)
                    .build());

            bookRepository.save(Book.builder()
                    .title("Effective Java")
                    .author("Joshua Bloch")
                    .isbn("978-0134685991")
                    .publisher("Addison-Wesley")
                    .publicationYear(2018)
                    .price(new BigDecimal("54.99"))
                    .quantity(40)
                    .description("Best practices and design techniques for writing high-quality Java code")
                    .available(true)
                    .build());

            bookRepository.save(Book.builder()
                    .title("Java: The Complete Reference")
                    .author("Herbert Schildt")
                    .isbn("978-1260463415")
                    .publisher("McGraw-Hill Education")
                    .publicationYear(2021)
                    .price(new BigDecimal("59.99"))
                    .quantity(20)
                    .description("Comprehensive reference covering the Java programming language")
                    .available(true)
                    .build());

            bookRepository.save(Book.builder()
                    .title("Head First Java")
                    .author("Kathy Sierra & Bert Bates")
                    .isbn("978-1491910771")
                    .publisher("O'Reilly Media")
                    .publicationYear(2022)
                    .price(new BigDecimal("44.99"))
                    .quantity(35)
                    .description("Beginner-friendly guide to learning Java through practical examples")
                    .available(true)
                    .build());

            bookRepository.save(Book.builder()
                    .title("Beginning Spring Boot 3")
                    .author("Greg L. Turnquist")
                    .isbn("978-1484298237")
                    .publisher("Apress")
                    .publicationYear(2024)
                    .price(new BigDecimal("49.99"))
                    .quantity(18)
                    .description("Learn Spring Boot 3 fundamentals and build production-ready applications")
                    .available(true)
                    .build());

            bookRepository.save(Book.builder()
                    .title("Pro Spring Boot 3")
                    .author("Felipe Gutierrez")
                    .isbn("978-1484298077")
                    .publisher("Apress")
                    .publicationYear(2024)
                    .price(new BigDecimal("59.99"))
                    .quantity(15)
                    .description("Advanced Spring Boot concepts for enterprise application development")
                    .available(true)
                    .build());

            bookRepository.save(Book.builder()
                    .title("Java Concurrency in Practice")
                    .author("Brian Goetz")
                    .isbn("978-0321349606")
                    .publisher("Addison-Wesley")
                    .publicationYear(2006)
                    .price(new BigDecimal("52.99"))
                    .quantity(22)
                    .description("Essential techniques for writing safe and efficient concurrent Java applications")
                    .available(true)
                    .build());

            bookRepository.save(Book.builder()
                    .title("Clean Code")
                    .author("Robert C. Martin")
                    .isbn("978-0132350884")
                    .publisher("Prentice Hall")
                    .publicationYear(2008)
                    .price(new BigDecimal("47.99"))
                    .quantity(50)
                    .description("A handbook of agile software craftsmanship with clean coding principles")
                    .available(true)
                    .build());

            bookRepository.save(Book.builder()
                    .title("Cloud Native Java")
                    .author("Josh Long & Kenny Bastani")
                    .isbn("978-1449374648")
                    .publisher("O'Reilly Media")
                    .publicationYear(2017)
                    .price(new BigDecimal("46.99"))
                    .quantity(28)
                    .description("Build resilient cloud-native applications using Java and Spring Boot")
                    .available(true)
                    .build());

            log.info("Successfully added {} sample books to the database", bookRepository.count());
        } else {
            log.info("Database already contains {} books. Skipping initialization.", bookRepository.count());
        }
    }
}
