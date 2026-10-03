package org.example.library;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BookDAO bookDAO = new BookDAO();
        MemberDAO memberDAO = new MemberDAO();
        IssueDAO issueDAO = new IssueDAO();

        while (true) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("     LIBRARY MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add Member");
            System.out.println("4. View Members");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Issued Books");
            System.out.println("8. Search Book");
            System.out.println("9. Exit");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();

                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();

                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    Book book = new Book(
                            title,
                            author,
                            isbn,
                            quantity
                    );

                    bookDAO.addBook(book);

                    break;


                case 2:

                    bookDAO.viewBooks();

                    break;


                case 3:

                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter phone: ");
                    String phone = scanner.nextLine();

                    Member member = new Member(
                            name,
                            email,
                            phone
                    );

                    memberDAO.addMember(member);

                    break;


                case 4:

                    memberDAO.viewMembers();

                    break;


                case 5:

                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();

                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();

                    scanner.nextLine();

                    issueDAO.issueBook(
                            bookId,
                            memberId
                    );

                    break;


                case 6:

                    System.out.print("Enter issue ID: ");
                    int issueId = scanner.nextInt();

                    scanner.nextLine();

                    issueDAO.returnBook(issueId);

                    break;


                case 7:

                    issueDAO.viewIssuedBooks();

                    break;


                case 8:

                    System.out.print("Enter book title or author: ");
                    String keyword = scanner.nextLine();

                    bookDAO.searchBook(keyword);

                    break;


                case 9:

                    System.out.println(
                            "Thank you for using the Library Management System!"
                    );

                    scanner.close();

                    return;


                default:

                    System.out.println(
                            "Invalid choice! Please try again."
                    );
            }
        }
    }
}