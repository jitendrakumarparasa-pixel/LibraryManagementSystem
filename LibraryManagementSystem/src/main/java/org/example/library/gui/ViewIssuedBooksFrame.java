package org.example.library.gui;

import org.example.library.IssueDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.example.library.DBConnection;

public class ViewIssuedBooksFrame extends JFrame {

    private JTable table;

    public ViewIssuedBooksFrame() {

        setTitle("View Issued Books");

        setSize(800, 500);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.BLACK);


        // =========================
        // HEADER
        // =========================

        JLabel heading = new JLabel(
                "📋 ISSUED BOOKS",
                SwingConstants.CENTER
        );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        heading.setForeground(Color.WHITE);

        heading.setBackground(Color.BLACK);

        heading.setOpaque(true);

        heading.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        10,
                        20,
                        10
                )
        );

        add(
                heading,
                BorderLayout.NORTH
        );


        // =========================
        // TABLE
        // =========================

        String[] columns = {
                "Issue ID",
                "Book",
                "Member",
                "Issue Date",
                "Return Date"
        };

        DefaultTableModel model =
                new DefaultTableModel(
                        columns,
                        0
                );

        table =
                new JTable(model);

        table.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        table.setRowHeight(30);

        table.setBackground(Color.WHITE);

        table.setForeground(Color.BLACK);

        table.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(table);

        add(
                scrollPane,
                BorderLayout.CENTER
        );


        // =========================
        // BUTTON PANEL
        // =========================

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.setBackground(Color.BLACK);

        buttonPanel.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        15,
                        15
                )
        );


        JButton refreshButton =
                createButton("🔄 Refresh");

        JButton closeButton =
                createButton("❌ Close");


        buttonPanel.add(refreshButton);

        buttonPanel.add(closeButton);


        add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        // =========================
        // BUTTON ACTIONS
        // =========================

        refreshButton.addActionListener(e -> {

            loadIssuedBooks();

        });


        closeButton.addActionListener(e -> {

            dispose();

        });


        // =========================
        // LOAD DATA
        // =========================

        loadIssuedBooks();
    }


    // =========================
    // LOAD ISSUED BOOKS
    // =========================

    private void loadIssuedBooks() {

        DefaultTableModel model =
                (DefaultTableModel) table.getModel();

        model.setRowCount(0);


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
                ORDER BY issued_books.id
                """;


        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                model.addRow(
                        new Object[]{
                                resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getString("name"),
                                resultSet.getDate("issue_date"),
                                resultSet.getDate("return_date")
                        }
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to load issued books!",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }


    // =========================
    // CREATE BUTTON
    // =========================

    private JButton createButton(String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(Color.WHITE);

        button.setBackground(
                new Color(
                        40,
                        40,
                        40
                )
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }
}