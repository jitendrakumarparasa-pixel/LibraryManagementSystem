package org.example.library.gui;

import org.example.library.IssueDAO;

import javax.swing.*;
import java.awt.*;

public class IssueBookFrame extends JFrame {

    private JTextField bookIdField;
    private JTextField memberIdField;

    private final IssueDAO issueDAO =
            new IssueDAO();


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public IssueBookFrame() {

        setTitle("Issue Book");

        setSize(600, 400);

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
                "📖 ISSUE BOOK",
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
        // FORM PANEL
        // =====================================================

        JPanel formPanel =
                new JPanel();

        formPanel.setLayout(
                new GridLayout(
                        2,
                        2,
                        15,
                        20
                )
        );

        formPanel.setBackground(Color.BLACK);

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        50,
                        60,
                        30,
                        60
                )
        );


        // =====================================================
        // BOOK ID
        // =====================================================

        JLabel bookIdLabel =
                createLabel("Book ID:");

        bookIdField =
                createTextField();


        // =====================================================
        // MEMBER ID
        // =====================================================

        JLabel memberIdLabel =
                createLabel("Member ID:");

        memberIdField =
                createTextField();


        formPanel.add(bookIdLabel);
        formPanel.add(bookIdField);

        formPanel.add(memberIdLabel);
        formPanel.add(memberIdField);


        add(
                formPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // BUTTON PANEL
        // =====================================================

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


        JButton issueButton =
                createButton("📖 Issue Book");

        JButton clearButton =
                createButton("🧹 Clear");

        JButton closeButton =
                createButton("❌ Close");


        buttonPanel.add(issueButton);

        buttonPanel.add(clearButton);

        buttonPanel.add(closeButton);


        add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        // =====================================================
        // ISSUE BUTTON
        // =====================================================

        issueButton.addActionListener(e -> {

            issueBook();

        });


        // =====================================================
        // CLEAR BUTTON
        // =====================================================

        clearButton.addActionListener(e -> {

            clearFields();

        });


        // =====================================================
        // CLOSE BUTTON
        // =====================================================

        closeButton.addActionListener(e -> {

            dispose();

        });
    }


    // =====================================================
    // ISSUE BOOK
    // =====================================================

    private void issueBook() {

        String bookIdText =
                bookIdField.getText().trim();

        String memberIdText =
                memberIdField.getText().trim();


        // =====================================================
        // CHECK EMPTY
        // =====================================================

        if (bookIdText.isEmpty() ||
                memberIdText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Book ID and Member ID!",
                    "Missing Details",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // =====================================================
        // CONVERT IDs
        // =====================================================

        int bookId;

        int memberId;


        try {

            bookId =
                    Integer.parseInt(bookIdText);

            memberId =
                    Integer.parseInt(memberIdText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Book ID and Member ID must be numbers!",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // =====================================================
        // ISSUE BOOK
        // =====================================================

        boolean success =
                issueDAO.issueBook(
                        bookId,
                        memberId
                );


        // =====================================================
        // CHECK RESULT
        // =====================================================

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Book issued successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Book could not be issued.\n\n" +
                            "Please check:\n" +
                            "• Book ID\n" +
                            "• Member ID\n" +
                            "• Book availability",
                    "Issue Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        bookIdField.setText("");

        memberIdField.setText("");

        bookIdField.requestFocus();
    }


    // =====================================================
    // CREATE LABEL
    // =====================================================

    private JLabel createLabel(String text) {

        JLabel label =
                new JLabel(text);

        label.setForeground(Color.WHITE);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        return label;
    }


    // =====================================================
    // CREATE TEXT FIELD
    // =====================================================

    private JTextField createTextField() {

        JTextField field =
                new JTextField();

        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        field.setForeground(Color.BLACK);

        field.setBackground(Color.WHITE);

        field.setCaretColor(Color.BLACK);

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.GRAY
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                8,
                                5,
                                8
                        )
                )
        );

        return field;
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