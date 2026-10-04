package org.example.library.gui;

import org.example.library.IssueDAO;

import javax.swing.*;
import java.awt.*;

public class ReturnBookFrame extends JFrame {

    private JTextField issueIdField;

    private final IssueDAO issueDAO =
            new IssueDAO();


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public ReturnBookFrame() {

        setTitle("Return Book");

        setSize(600, 350);

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
                "↩ RETURN BOOK",
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
                        1,
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


        JLabel issueIdLabel =
                createLabel("Issue ID:");

        issueIdField =
                createTextField();


        formPanel.add(issueIdLabel);

        formPanel.add(issueIdField);


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


        JButton returnButton =
                createButton("↩ Return Book");

        JButton clearButton =
                createButton("🧹 Clear");

        JButton closeButton =
                createButton("❌ Close");


        buttonPanel.add(returnButton);

        buttonPanel.add(clearButton);

        buttonPanel.add(closeButton);


        add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        // =====================================================
        // RETURN BUTTON
        // =====================================================

        returnButton.addActionListener(e -> {

            returnBook();

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
    // RETURN BOOK
    // =====================================================

    private void returnBook() {

        String issueIdText =
                issueIdField.getText().trim();


        // =====================================================
        // CHECK EMPTY
        // =====================================================

        if (issueIdText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Issue ID!",
                    "Missing Details",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // =====================================================
        // CONVERT ISSUE ID
        // =====================================================

        int issueId;

        try {

            issueId =
                    Integer.parseInt(issueIdText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Issue ID must be a number!",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // =====================================================
        // RETURN BOOK
        // =====================================================

        issueDAO.returnBook(issueId);


        JOptionPane.showMessageDialog(
                this,
                "Return operation completed.\n\n" +
                        "Check the issued books list " +
                        "to verify the return date.",
                "Return Book",
                JOptionPane.INFORMATION_MESSAGE
        );


        clearFields();
    }


    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        issueIdField.setText("");

        issueIdField.requestFocus();
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