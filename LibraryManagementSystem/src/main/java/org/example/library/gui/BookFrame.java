package org.example.library.gui;

import org.example.library.Book;
import org.example.library.BookDAO;

import javax.swing.*;
import java.awt.*;

public class BookFrame extends JFrame {

    private JTextField titleField;
    private JTextField authorField;
    private JTextField isbnField;
    private JTextField quantityField;

    private final BookDAO bookDAO = new BookDAO();

    public BookFrame() {

        setTitle("Book Management");

        setSize(600, 500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        // =========================
        // BLACK BACKGROUND
        // =========================

        getContentPane().setBackground(Color.BLACK);


        // =========================
        // TITLE
        // =========================

        JLabel heading = new JLabel(
                "📚 ADD BOOK",
                SwingConstants.CENTER
        );

        heading.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        heading.setForeground(Color.WHITE);

        heading.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 10, 20, 10
                )
        );

        add(heading, BorderLayout.NORTH);


        // =========================
        // FORM
        // =========================

        JPanel formPanel = new JPanel();

        formPanel.setLayout(
                new GridLayout(4, 2, 15, 15)
        );

        formPanel.setBackground(Color.BLACK);

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 50, 30, 50
                )
        );


        // Title

        JLabel titleLabel =
                createLabel("Book Title:");

        titleField =
                new JTextField();

        // Author

        JLabel authorLabel =
                createLabel("Author:");

        authorField =
                new JTextField();

        // ISBN

        JLabel isbnLabel =
                createLabel("ISBN:");

        isbnField =
                new JTextField();

        // Quantity

        JLabel quantityLabel =
                createLabel("Quantity:");

        quantityField =
                new JTextField();


        formPanel.add(titleLabel);
        formPanel.add(titleField);

        formPanel.add(authorLabel);
        formPanel.add(authorField);

        formPanel.add(isbnLabel);
        formPanel.add(isbnField);

        formPanel.add(quantityLabel);
        formPanel.add(quantityField);


        add(formPanel, BorderLayout.CENTER);


        // =========================
        // BUTTONS
        // =========================

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.setBackground(Color.BLACK);

        JButton addButton =
                new JButton("➕ Add Book");

        JButton clearButton =
                new JButton("🧹 Clear");

        JButton closeButton =
                new JButton("❌ Close");


        styleButton(addButton);
        styleButton(clearButton);
        styleButton(closeButton);


        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(closeButton);


        add(buttonPanel, BorderLayout.SOUTH);


        // =========================
        // ADD BOOK
        // =========================

        addButton.addActionListener(e -> addBook());


        // =========================
        // CLEAR
        // =========================

        clearButton.addActionListener(e -> {

            titleField.setText("");
            authorField.setText("");
            isbnField.setText("");
            quantityField.setText("");

        });


        // =========================
        // CLOSE
        // =========================

        closeButton.addActionListener(e -> {

            dispose();

        });
    }


    // =========================
    // ADD BOOK METHOD
    // =========================

    private void addBook() {

        String title =
                titleField.getText().trim();

        String author =
                authorField.getText().trim();

        String isbn =
                isbnField.getText().trim();

        String quantityText =
                quantityField.getText().trim();


        // Check empty fields

        if (title.isEmpty() ||
                author.isEmpty() ||
                isbn.isEmpty() ||
                quantityText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter all details!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // Convert quantity

        int quantity;

        try {

            quantity =
                    Integer.parseInt(quantityText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Quantity must be a number!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // Check quantity

        if (quantity <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Quantity must be greater than 0!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // Create Book object

        Book book =
                new Book(
                        title,
                        author,
                        isbn,
                        quantity
                );


        // Save to PostgreSQL

        bookDAO.addBook(book);


        JOptionPane.showMessageDialog(
                this,
                "Book added successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );


        // Clear fields

        titleField.setText("");
        authorField.setText("");
        isbnField.setText("");
        quantityField.setText("");
    }


    // =========================
    // LABEL DESIGN
    // =========================

    private JLabel createLabel(String text) {

        JLabel label =
                new JLabel(text);

        label.setForeground(Color.WHITE);

        label.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        return label;
    }


    // =========================
    // BUTTON DESIGN
    // =========================

    private void styleButton(JButton button) {

        button.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        button.setForeground(Color.WHITE);

        button.setBackground(
                new Color(40, 40, 40)
        );

        button.setFocusPainted(false);
    }
}