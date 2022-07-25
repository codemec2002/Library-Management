package com.example.dbconnection.Repository;

import com.example.dbconnection.Model.Book;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    private Connection connection;
    private String table_name = "myBooks";

    public void getConnection() throws SQLException {
        if (connection == null)
        {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "root" );
        }

    }

    public void closeConnection()
    {
        connection = null;
    }

    public String createTable(String table_name) throws SQLException {

        getConnection();

        this.table_name = table_name;

        Statement statement = connection.createStatement();

        boolean isExecuted = statement.execute("CREATE TABLE " + table_name + "(id INT primary key, book_name VARCHAR(30),author_name VARCHAR (30),cost int)");

         return "table " + table_name + " executed";
//        else return "table" + table_name + " executed";

    }

    public String save(Book book) throws SQLException {

        getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(("INSERT INTO " + table_name + " VALUES (?,?,?,?)"));
        preparedStatement.setInt(1,book.getId());
        preparedStatement.setString(2, book.getName());
        preparedStatement.setString(3, book.getAuthorName());
        preparedStatement.setInt(4,book.getCost());

        int row = preparedStatement.executeUpdate();

        if (row == 0) return "Cannot save the book\n" ;
        else return "book" + book.getName() + " Saved";
    }

    public List<Book> getAllbook() throws SQLException {

        getConnection();

        ArrayList<Book> books = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table_name);

        while (resultSet.next())
        {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String authorName = resultSet.getString(3);
            int cost = resultSet.getInt(4);

            Book book = new Book(name,id,authorName,cost);
            books.add(book);
        }
        return books;
    }

    public Book getBookById(int id) throws SQLException {
        getConnection();
        Book currbook = null;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table_name);

        while (resultSet.next())
        {
            int currId = resultSet.getInt(1);
            if (currId == id)
            {
                String name = resultSet.getString(2);
                String authorName = resultSet.getString(3);
                int cost = resultSet.getInt(4);
                currbook = new Book(name,id,authorName,cost);

                break;
            }
        }
        return currbook;
    }


    public void deleteById(int id) throws SQLException {
        getConnection();

        Statement statement = connection.createStatement();
        Book book = getBookById(id);
        if(book == null)
            return;
        int resultSet = statement.executeUpdate("DELETE FROM " + table_name + " t where t.id = " + book.getId());
    }

}
