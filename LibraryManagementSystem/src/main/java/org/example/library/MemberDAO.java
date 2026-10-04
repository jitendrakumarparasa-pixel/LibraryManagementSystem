package org.example.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {

    // =====================================================
    // ADD MEMBER
    // =====================================================

    public void addMember(Member member) {

        String sql =
                "INSERT INTO members (name, email, phone) " +
                        "VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, member.getName());
            statement.setString(2, member.getEmail());
            statement.setString(3, member.getPhone());

            statement.executeUpdate();

            System.out.println("Member added successfully!");

        } catch (Exception e) {

            System.out.println("Failed to add member.");
            e.printStackTrace();
        }
    }


    // =====================================================
    // GET ALL MEMBERS
    // =====================================================

    public ResultSet getAllMembers() {

        String sql =
                "SELECT * FROM members ORDER BY id";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            return statement.executeQuery();

        } catch (Exception e) {

            System.out.println(
                    "Failed to retrieve members."
            );

            e.printStackTrace();

            return null;
        }
    }


    // =====================================================
    // VIEW MEMBERS IN CONSOLE
    // =====================================================

    public void viewMembers() {

        String sql =
                "SELECT * FROM members";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            System.out.println(
                    "\n========== ALL MEMBERS =========="
            );

            while (resultSet.next()) {

                int id =
                        resultSet.getInt("id");

                String name =
                        resultSet.getString("name");

                String email =
                        resultSet.getString("email");

                String phone =
                        resultSet.getString("phone");


                System.out.println(
                        "ID    : " + id
                );

                System.out.println(
                        "Name  : " + name
                );

                System.out.println(
                        "Email : " + email
                );

                System.out.println(
                        "Phone : " + phone
                );

                System.out.println(
                        "-------------------------------"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed to retrieve members."
            );

            e.printStackTrace();
        }
    }
}