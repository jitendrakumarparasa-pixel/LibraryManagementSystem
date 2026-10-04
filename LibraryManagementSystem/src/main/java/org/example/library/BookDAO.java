package org.example.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDAO {

    // =====================================================
    // ADD BOOK
    // =====================================================

    public void addBook(Book book) {

        String sql =
                "INSERT INTO books (title, author, isbn, quantity) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setInt(4, book.getQuantity());

            statement.executeUpdate();

            System.out.println("Book added successfully!");

        } catch (Exception e) {

            System.out.println("Failed to add book.");
            e.printStackTrace();
        }
    }


    // =====================================================
    // VIEW ALL BOOKS
    // =====================================================

    public ResultSet getAllBooks() {

        String sql = "SELECT * FROM books ORDER BY id";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            return statement.executeQuery();

        } catch (Exception e) {

            System.out.println("Failed to retrieve books.");
            e.printStackTrace();

            return null;
        }
    }


    // =====================================================
    // SEARCH BOOK
    // =====================================================

    public void searchBook(String keyword) {

        String sql = """
                SELECT * FROM books
                WHERE LOWER(title) LIKE LOWER(?)
                   OR LOWER(author) LIKE LOWER(?)
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    "%" + keyword + "%"
            );

            statement.setString(
                    2,
                    "%" + keyword + "%"
            );

            ResultSet resultSet =
                    statement.executeQuery();

            System.out.println(
                    "\n========== SEARCH RESULTS =========="
            );

            boolean found = false;

            while (resultSet.next()) {

                found = true;

                int id =
                        resultSet.getInt("id");

                String title =
                        resultSet.getString("title");

                String author =
                        resultSet.getString("author");

                String isbn =
                        resultSet.getString("isbn");

                int quantity =
                        resultSet.getInt("quantity");

                System.out.println(
                        "ID       : " + id
                );

                System.out.println(
                        "Title    : " + title
                );

                System.out.println(
                        "Author   : " + author
                );

                System.out.println(
                        "ISBN     : " + isbn
                );

                System.out.println(
                        "Quantity : " + quantity
                );

                System.out.println(
                        "-------------------------------"
                );
            }

            if (!found) {

                System.out.println(
                        "No books found!"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed to search books."
            );

            e.printStackTrace();
        }
    }
}