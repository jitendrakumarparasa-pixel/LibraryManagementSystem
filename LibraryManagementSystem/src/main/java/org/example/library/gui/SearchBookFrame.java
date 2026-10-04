package org.example.library.gui;

import org.example.library.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchBookFrame extends JFrame {

    private JTextField searchField;
    private JTable table;


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public SearchBookFrame() {

        setTitle("Search Book");

        setSize(800, 500);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.BLACK);


        // =====================================================
        // HEADER
        // =====================================================

        JLabel heading = new JLabel(
                "🔎 SEARCH BOOK",
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


        // =====================================================
        // SEARCH PANEL
        // =====================================================

        JPanel searchPanel =
                new JPanel();

        searchPanel.setBackground(Color.BLACK);

        searchPanel.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        10,
                        15
                )
        );


        JLabel searchLabel =
                new JLabel("Search:");

        searchLabel.setForeground(Color.WHITE);

        searchLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );


        searchField =
                new JTextField(25);

        searchField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );


        JButton searchButton =
                createButton("🔎 Search");


        searchPanel.add(searchLabel);

        searchPanel.add(searchField);

        searchPanel.add(searchButton);


        add(
                searchPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // TABLE
        // =====================================================

        String[] columns = {
                "ID",
                "Title",
                "Author",
                "ISBN",
                "Quantity"
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
                BorderLayout.SOUTH
        );


        // =====================================================
        // BUTTON ACTION
        // =====================================================

        searchButton.addActionListener(e -> {

            searchBooks();

        });


        // =====================================================
        // ENTER KEY
        // =====================================================

        searchField.addActionListener(e -> {

            searchBooks();

        });
    }


    // =====================================================
    // SEARCH BOOKS
    // =====================================================

    private void searchBooks() {

        String keyword =
                searchField.getText().trim();


        if (keyword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a book title or author!",
                    "Missing Search",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        DefaultTableModel model =
                (DefaultTableModel) table.getModel();

        model.setRowCount(0);


        String sql = """
                SELECT *
                FROM books
                WHERE LOWER(title) LIKE LOWER(?)
                   OR LOWER(author) LIKE LOWER(?)
                ORDER BY id
                """;


        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

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


            boolean found = false;


            while (resultSet.next()) {

                found = true;


                model.addRow(
                        new Object[]{
                                resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getString("author"),
                                resultSet.getString("isbn"),
                                resultSet.getInt("quantity")
                        }
                );
            }


            if (!found) {

                JOptionPane.showMessageDialog(
                        this,
                        "No books found!",
                        "Search Result",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }


        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to search books!",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }


    // =====================================================
    // CREATE BUTTON
    // =====================================================

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