package com.mycompany.library;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        UserManager users = new UserManager();
        
        
        library.loadFromFile("books.txt");
        
        
        System.out.println("=== Welcome to the Library System ===");
        System.out.println("1. Login (existing member)");
        System.out.println("2. Sign in (new member)");
        System.out.print("Choose: ");
        int loginChoice = scanner.nextInt();
        scanner.nextLine();
        
        Member currentUser = null;
        
        switch (loginChoice) {
            case 1 -> currentUser = users.login(scanner);
            case 2 -> currentUser = users.signIn(scanner);
            default -> System.out.println("Invalid option.");
        }
        
        if (currentUser == null) {
            System.out.println("Login failed. Exiting.");
            scanner.close();
            return;
        }
        
        library.restoreBorrowedBooks(currentUser);
        
        boolean isAdmin = (currentUser instanceof Admin);
        boolean running = true;
        
        while (running) {
            System.out.println("\n===== Menu =====");
            if (isAdmin) {
                System.out.println("1. Add Book");
                System.out.println("2. Remove Book");
            }
            System.out.println("3. Search by Title");
            System.out.println("4. Search by Author");
            System.out.println("5. Display All Books");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. My Borrowed Books");
            System.out.println("9. Sort by Title");
            System.out.println("10. Sort by Author");
            System.out.println("11. Save to File");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
    
            
            switch (choice) {
                case 1 -> {
                    if (isAdmin) {
                        System.out.print("Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Author: ");
                        String author = scanner.nextLine();
                        System.out.print("ISBN: ");
                        String isbn = scanner.nextLine();
                        library.addBook(new Book(title, author, isbn), currentUser);
                        library.saveToFile("books.txt");
                    } else {
                        System.out.println("Invalid option.");
                    }
                }
                case 2 -> {
                    if (isAdmin) {
                        System.out.print("Enter ISBN to remove: ");
                        String isbnToRemove = scanner.nextLine();
                        library.removeBook(isbnToRemove, currentUser);
                        library.saveToFile("books.txt");
                    } else {
                        System.out.println("Invalid option.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter title to search: ");
                    String searchTitle = scanner.nextLine();
                    library.searchByTitle(searchTitle);
                }
                case 4 -> {
                    System.out.print("Enter author to search: ");
                    String searchAuthor = scanner.nextLine();
                    library.searchByAuthor(searchAuthor);
                }
                case 5 -> library.displayAllBooks();
                case 6 -> { 
                    System.out.print("Enter ISBN to borrow: ");
                    String isbnToBorrow = scanner.nextLine();
                    library.borrowBook(isbnToBorrow, currentUser);
                    library.saveToFile("books.txt");
                }
                case 7 -> {
                    System.out.print("Enter ISBN to return: ");
                    String isbnToReturn = scanner.nextLine();
                    library.returnBook(isbnToReturn, currentUser);
                    library.saveToFile("books.txt");
                }
                case 8 -> library.displayBorrowedBooks(currentUser);
                case 9 -> {
                    library.sortByTitle();
                    library.displayAllBooks();
                    library.saveToFile("books.txt");
                } 
                case 10 -> {
                    library.sortByAuthor();
                    library.displayAllBooks();
                    library.saveToFile("books.txt");
                }
                case 11 ->  library.saveToFile("books.txt");
                case 0 -> {
                    library.saveToFile("books.txt");
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid option, try again.");
            }
        }
        scanner.close();
    }
}
