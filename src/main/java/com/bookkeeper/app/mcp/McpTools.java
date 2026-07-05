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
}
