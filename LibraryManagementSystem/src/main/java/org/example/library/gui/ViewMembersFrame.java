package org.example.library.gui;

import org.example.library.MemberDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class ViewMembersFrame extends JFrame {

    private JTable memberTable;
    private DefaultTableModel tableModel;

    private final MemberDAO memberDAO = new MemberDAO();


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public ViewMembersFrame() {

        setTitle("View Members");

        setSize(800, 500);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.BLACK);


        // =====================================================
        // HEADER
        // =====================================================

        JLabel heading = new JLabel(
                "👥 ALL MEMBERS",
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
                "Name",
                "Email",
                "Phone"
        };


        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                );


        memberTable =
                new JTable(tableModel);


        memberTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );


        memberTable.setRowHeight(30);


        memberTable.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );


        memberTable.getTableHeader().setBackground(
                new Color(40, 40, 40)
        );


        memberTable.getTableHeader().setForeground(
                Color.WHITE
        );


        memberTable.setBackground(Color.WHITE);

        memberTable.setForeground(Color.BLACK);


        JScrollPane scrollPane =
                new JScrollPane(memberTable);


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

            loadMembers();

        });


        closeButton.addActionListener(e -> {

            dispose();

        });


        // Load members when window opens

        loadMembers();
    }


    // =====================================================
    // LOAD MEMBERS
    // =====================================================

    private void loadMembers() {

        tableModel.setRowCount(0);


        try {

            ResultSet resultSet =
                    memberDAO.getAllMembers();


            if (resultSet == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to load members.",
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


                String name =
                        resultSet.getString("name");


                String email =
                        resultSet.getString("email");


                String phone =
                        resultSet.getString("phone");


                tableModel.addRow(
                        new Object[]{
                                id,
                                name,
                                email,
                                phone
                        }
                );
            }


            if (!found) {

                JOptionPane.showMessageDialog(
                        this,
                        "No members found in the database.",
                        "Members",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }


        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading members:\n" +
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