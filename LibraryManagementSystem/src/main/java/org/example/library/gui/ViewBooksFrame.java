package org.example.library.gui;

import org.example.library.BookDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class ViewBooksFrame extends JFrame {

    private JTable bookTable;
    private DefaultTableModel tableModel;

    private final BookDAO bookDAO = new BookDAO();


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public ViewBooksFrame() {

        setTitle("View Books");

        setSize(800, 500);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.BLACK);


        // =====================================================
        // HEADER
        // =====================================================

        JLabel heading = new JLabel(
                "📚 ALL BOOKS",
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
                        20, 10, 20, 10
                )
        );

        add(
                heading,
                BorderLayout.NORTH
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


        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                );


        bookTable =
                new JTable(tableModel);


        bookTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );


        bookTable.setRowHeight(30);


        bookTable.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );


        bookTable.getTableHeader().setBackground(
                new Color(40, 40, 40)
        );


        bookTable.getTableHeader().setForeground(
                Color.WHITE
        );


        bookTable.setBackground(Color.WHITE);

        bookTable.setForeground(Color.BLACK);


        JScrollPane scrollPane =
                new JScrollPane(bookTable);


        add(
                scrollPane,
                BorderLayout.CENTER
        );


        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.setBackground(Color.BLACK);


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


        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        refreshButton.addActionListener(e -> {

            loadBooks();

        });


        closeButton.addActionListener(e -> {

            dispose();

        });


        // Load books when window opens

        loadBooks();
    }


    // =====================================================
    // LOAD BOOKS
    // =====================================================

    private void loadBooks() {

        // Remove old rows

        tableModel.setRowCount(0);


        try {

            ResultSet resultSet =
                    bookDAO.getAllBooks();


            if (resultSet == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to load books.",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


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


                tableModel.addRow(
                        new Object[]{
                                id,
                                title,
                                author,
                                isbn,
                                quantity
                        }
                );
            }


            if (!found) {

                JOptionPane.showMessageDialog(
                        this,
                        "No books found in the database.",
                        "Books",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }


        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading books:\n" +
                            e.getMessage(),
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