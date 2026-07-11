package com.bookkeeper.app.mcp;


import com.bookkeeper.app.entity.Book;
import com.bookkeeper.app.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@RequiredArgsConstructor
public class McpTools {
    private final BookService bookService;


    @McpTool(name = "get_all_books", description = "Get all books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @McpTool(name = "get_book_by_id", description = "Get book by id")
    public Book getBookById(Long id) {
        return bookService.getBookById(id);
    }

    @McpTool(name = "create_book", description = "Create book and make entry in db")
    public Book createBook(Book book) {
        return bookService.createBook(book);
    }

    @McpTool(name = "update_book", description = "Update book for a given id")
    public Book updateBook(Long id, Book book) {
        return bookService.updateBook(id, book);
    }

    @McpTool(name = "delete_book", description = "Delete book for a given id")
    public void deleteBook(Long id) {
        bookService.deleteBook(id);
    }


}
