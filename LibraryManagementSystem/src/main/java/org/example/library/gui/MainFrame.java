package org.example.library.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Library Management System");

        setSize(1000, 650);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.BLACK);


        // =========================
        // HEADER
        // =========================

        JLabel title = new JLabel(
                "📚 LIBRARY MANAGEMENT SYSTEM",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(Color.WHITE);

        title.setBackground(Color.BLACK);

        title.setOpaque(true);

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        10,
                        25,
                        10
                )
        );

        add(title, BorderLayout.NORTH);


        // =========================
        // LEFT MENU
        // =========================

        JPanel menuPanel = new JPanel();

        menuPanel.setLayout(
                new GridLayout(
                        9,
                        1,
                        8,
                        8
                )
        );

        menuPanel.setBackground(Color.BLACK);

        menuPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );


        JButton booksButton =
                createButton("📚 Books");

        JButton viewBooksButton =
                createButton("📋 View Books");

        JButton membersButton =
                createButton("👥 Members");

        JButton viewMembersButton =
                createButton("📋 View Members");

        JButton issueButton =
                createButton("📖 Issue Book");

        JButton viewIssuedButton =
                createButton("📋 View Issued Books");

        JButton returnButton =
                createButton("↩ Return Book");

        JButton searchButton =
                createButton("🔎 Search Book");

        JButton exitButton =
                createButton("❌ Exit");


        menuPanel.add(booksButton);
        menuPanel.add(viewBooksButton);
        menuPanel.add(membersButton);
        menuPanel.add(viewMembersButton);
        menuPanel.add(issueButton);
        menuPanel.add(viewIssuedButton);
        menuPanel.add(returnButton);
        menuPanel.add(searchButton);
        menuPanel.add(exitButton);


        add(
                menuPanel,
                BorderLayout.WEST
        );


        // =========================
        // CENTER
        // =========================

        JPanel centerPanel =
                new JPanel();

        centerPanel.setBackground(Color.BLACK);

        centerPanel.setLayout(
                new GridBagLayout()
        );


        JLabel welcomeLabel =
                new JLabel(
                        "<html><center>" +
                                "Welcome to<br><br>" +
                                "Library Management System" +
                                "</center></html>",
                        SwingConstants.CENTER
                );

        // Handwriting / Cursive Font
        welcomeLabel.setFont(
                new Font(
                        "Segoe Script",
                        Font.BOLD,
                        30
                )
        );

        welcomeLabel.setForeground(Color.WHITE);


        centerPanel.add(
                welcomeLabel
        );


        add(
                centerPanel,
                BorderLayout.CENTER
        );


        // =========================
        // BUTTON ACTIONS
        // =========================

        booksButton.addActionListener(e -> {

            BookFrame frame =
                    new BookFrame();

            frame.setVisible(true);
        });


        viewBooksButton.addActionListener(e -> {

            ViewBooksFrame frame =
                    new ViewBooksFrame();

            frame.setVisible(true);
        });


        membersButton.addActionListener(e -> {

            MemberFrame frame =
                    new MemberFrame();

            frame.setVisible(true);
        });


        viewMembersButton.addActionListener(e -> {

            ViewMembersFrame frame =
                    new ViewMembersFrame();

            frame.setVisible(true);
        });


        issueButton.addActionListener(e -> {

            IssueBookFrame frame =
                    new IssueBookFrame();

            frame.setVisible(true);
        });


        viewIssuedButton.addActionListener(e -> {

            ViewIssuedBooksFrame frame =
                    new ViewIssuedBooksFrame();

            frame.setVisible(true);
        });


        returnButton.addActionListener(e -> {

            ReturnBookFrame frame =
                    new ReturnBookFrame();

            frame.setVisible(true);
        });


        searchButton.addActionListener(e -> {

            SearchBookFrame frame =
                    new SearchBookFrame();

            frame.setVisible(true);
        });


        exitButton.addActionListener(e -> {

            int result =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Do you want to exit?",
                            "Exit",
                            JOptionPane.YES_NO_OPTION
                    );

            if (result ==
                    JOptionPane.YES_OPTION) {

                System.exit(0);
            }
        });
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
                        15
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

        button.setBorder(
                BorderFactory.createLineBorder(
                        new Color(80, 80, 80)
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }
}