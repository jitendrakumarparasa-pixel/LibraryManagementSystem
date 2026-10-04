package org.example.library.gui;

import org.example.library.Member;
import org.example.library.MemberDAO;

import javax.swing.*;
import java.awt.*;

public class MemberFrame extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;

    private final MemberDAO memberDAO = new MemberDAO();


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public MemberFrame() {

        setTitle("Member Management");

        setSize(650, 450);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.BLACK);


        // =====================================================
        // HEADER
        // =====================================================

        JLabel heading = new JLabel(
                "👥 MEMBER MANAGEMENT",
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
        // FORM PANEL
        // =====================================================

        JPanel formPanel = new JPanel();

        formPanel.setLayout(
                new GridLayout(3, 2, 15, 20)
        );

        formPanel.setBackground(Color.BLACK);

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        40, 60, 30, 60
                )
        );


        // =====================================================
        // NAME
        // =====================================================

        JLabel nameLabel =
                createLabel("Member Name:");

        nameField =
                createTextField();


        // =====================================================
        // EMAIL
        // =====================================================

        JLabel emailLabel =
                createLabel("Email:");

        emailField =
                createTextField();


        // =====================================================
        // PHONE
        // =====================================================

        JLabel phoneLabel =
                createLabel("Phone:");

        phoneField =
                createTextField();


        formPanel.add(nameLabel);
        formPanel.add(nameField);

        formPanel.add(emailLabel);
        formPanel.add(emailField);

        formPanel.add(phoneLabel);
        formPanel.add(phoneField);


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


        JButton addButton =
                createButton("➕ Add Member");

        JButton clearButton =
                createButton("🧹 Clear");

        JButton closeButton =
                createButton("❌ Close");


        buttonPanel.add(addButton);

        buttonPanel.add(clearButton);

        buttonPanel.add(closeButton);


        add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        // =====================================================
        // ADD MEMBER BUTTON
        // =====================================================

        addButton.addActionListener(e -> {

            addMember();

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
    // ADD MEMBER
    // =====================================================

    private void addMember() {

        String name =
                nameField.getText().trim();

        String email =
                emailField.getText().trim();

        String phone =
                phoneField.getText().trim();


        // =====================================================
        // CHECK EMPTY FIELDS
        // =====================================================

        if (name.isEmpty() ||
                email.isEmpty() ||
                phone.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter all member details!",
                    "Missing Details",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // =====================================================
        // CREATE MEMBER OBJECT
        // =====================================================

        Member member =
                new Member(
                        name,
                        email,
                        phone
                );


        // =====================================================
        // SAVE TO DATABASE
        // =====================================================

        memberDAO.addMember(member);


        // =====================================================
        // SUCCESS MESSAGE
        // =====================================================

        JOptionPane.showMessageDialog(
                this,
                "Member added successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );


        clearFields();
    }


    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        nameField.setText("");

        emailField.setText("");

        phoneField.setText("");

        nameField.requestFocus();
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