package com.example.dbconnection.Service;

import com.example.dbconnection.Model.Book;
import com.example.dbconnection.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public String save(Book book) throws SQLException {
        return bookRepository.save(book);
    }

    public List<Book> getAllbook() throws SQLException {
        return bookRepository.getAllbook();
    }

    public Book getBookById(int id) throws SQLException {
        return bookRepository.getBookById(id);
    }

    public String createTable(String table_name) throws SQLException {
        return bookRepository.createTable(table_name);
    }
}
