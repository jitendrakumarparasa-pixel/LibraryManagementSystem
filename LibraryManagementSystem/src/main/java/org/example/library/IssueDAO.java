package org.example.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IssueDAO {

    // =====================================================
    // ISSUE BOOK
    // =====================================================

    public boolean issueBook(int bookId, int memberId) {

        String checkBook =
                "SELECT quantity FROM books WHERE id = ?";

        String checkMember =
                "SELECT id FROM members WHERE id = ?";

        String insertIssue =
                "INSERT INTO issued_books (book_id, member_id) " +
                        "VALUES (?, ?)";

        String updateQuantity =
                "UPDATE books SET quantity = quantity - 1 " +
                        "WHERE id = ?";


        try (Connection connection =
                     DBConnection.getConnection()) {


            // =================================================
            // CHECK BOOK
            // =================================================

            try (PreparedStatement statement =
                         connection.prepareStatement(checkBook)) {

                statement.setInt(1, bookId);

                ResultSet resultSet =
                        statement.executeQuery();


                if (!resultSet.next()) {

                    System.out.println(
                            "Book not found!"
                    );

                    return false;
                }


                int quantity =
                        resultSet.getInt("quantity");


                if (quantity <= 0) {

                    System.out.println(
                            "Book is not available!"
                    );

                    return false;
                }
            }


            // =================================================
            // CHECK MEMBER
            // =================================================

            try (PreparedStatement statement =
                         connection.prepareStatement(checkMember)) {

                statement.setInt(1, memberId);

                ResultSet resultSet =
                        statement.executeQuery();


                if (!resultSet.next()) {

                    System.out.println(
                            "Member not found!"
                    );

                    return false;
                }
            }


            // =================================================
            // INSERT ISSUE RECORD
            // =================================================

            try (PreparedStatement statement =
                         connection.prepareStatement(insertIssue)) {

                statement.setInt(1, bookId);

                statement.setInt(2, memberId);

                statement.executeUpdate();
            }


            // =================================================
            // DECREASE BOOK QUANTITY
            // =================================================

            try (PreparedStatement statement =
                         connection.prepareStatement(updateQuantity)) {

                statement.setInt(1, bookId);

                statement.executeUpdate();
            }


            System.out.println(
                    "Book issued successfully!"
            );

            return true;


        } catch (Exception e) {

            System.out.println(
                    "Failed to issue book."
            );

            e.printStackTrace();

            return false;
        }
    }


    // =====================================================
    // RETURN BOOK
    // =====================================================

    public void returnBook(int issueId) {

        String findIssue =
                "SELECT book_id, return_date " +
                        "FROM issued_books WHERE id = ?";

        String updateIssue =
                "UPDATE issued_books " +
                        "SET return_date = CURRENT_DATE " +
                        "WHERE id = ?";

        String updateQuantity =
                "UPDATE books " +
                        "SET quantity = quantity + 1 " +
                        "WHERE id = ?";


        try (Connection connection =
                     DBConnection.getConnection()) {


            int bookId;


            // =================================================
            // FIND ISSUE RECORD
            // =================================================

            try (PreparedStatement statement =
                         connection.prepareStatement(findIssue)) {

                statement.setInt(1, issueId);

                ResultSet resultSet =
                        statement.executeQuery();


                if (!resultSet.next()) {

                    System.out.println(
                            "Issue record not found!"
                    );

                    return;
                }


                bookId =
                        resultSet.getInt("book_id");


                if (resultSet.getDate("return_date") != null) {

                    System.out.println(
                            "This book has already been returned!"
                    );

                    return;
                }
            }


            // =================================================
            // UPDATE RETURN DATE
            // =================================================

            try (PreparedStatement statement =
                         connection.prepareStatement(updateIssue)) {

                statement.setInt(1, issueId);

                statement.executeUpdate();
            }


            // =================================================
            // INCREASE BOOK QUANTITY
            // =================================================

            try (PreparedStatement statement =
                         connection.prepareStatement(updateQuantity)) {

                statement.setInt(1, bookId);

                statement.executeUpdate();
            }


            System.out.println(
                    "Book returned successfully!"
            );


        } catch (Exception e) {

            System.out.println(
                    "Failed to return book."
            );

            e.printStackTrace();
        }
    }


    // =====================================================
    // VIEW ISSUED BOOKS
    // =====================================================

    public void viewIssuedBooks() {

        String sql = """
                SELECT
                    issued_books.id,
                    books.title,
                    members.name,
                    issued_books.issue_date,
                    issued_books.return_date
                FROM issued_books
                JOIN books
                    ON issued_books.book_id = books.id
                JOIN members
                    ON issued_books.member_id = members.id
                """;


        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql);

             ResultSet resultSet =
                     statement.executeQuery()) {


            System.out.println(
                    "\n========== ISSUED BOOKS =========="
            );


            while (resultSet.next()) {

                int issueId =
                        resultSet.getInt("id");

                String title =
                        resultSet.getString("title");

                String memberName =
                        resultSet.getString("name");

                String issueDate =
                        resultSet.getString("issue_date");

                String returnDate =
                        resultSet.getString("return_date");


                System.out.println(
                        "Issue ID    : " + issueId
                );

                System.out.println(
                        "Book        : " + title
                );

                System.out.println(
                        "Member      : " + memberName
                );

                System.out.println(
                        "Issue Date  : " + issueDate
                );

                System.out.println(
                        "Return Date : " + returnDate
                );

                System.out.println(
                        "-------------------------------"
                );
            }


        } catch (Exception e) {

            System.out.println(
                    "Failed to retrieve issued books."
            );

            e.printStackTrace();
        }
    }
}