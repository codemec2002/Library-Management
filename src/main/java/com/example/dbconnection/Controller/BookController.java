package com.example.dbconnection.Controller;

import com.example.dbconnection.Model.Book;
import com.example.dbconnection.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController // Why RestController annotation
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/saveBook")
    public String saveBook(@RequestBody Book book) throws SQLException {
            return bookService.save(book);
    }

    @GetMapping("/getAllBook")
    public List<Book> getAllBook() throws SQLException {
         return bookService.getAllbook();
    }

    @GetMapping("/getBookById")
    public Book getBookById(@RequestParam(value = "id") int id) throws SQLException {
        return bookService.getBookById(id);
    }

    @PostMapping("/createTable/{table_name}")
    public String createTable(@PathVariable String table_name) throws SQLException {
        return bookService.createTable(table_name);
    }
}
