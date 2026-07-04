
package com.mycompany.library;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class Library {
    
    
    private ArrayList<Book> books;
    
    public Library() {
        books = new ArrayList<Book>();
    }
    
    
    public void addBook(Book book) {
        for (Book b : books) {
        if (b.getIsbn().equals(book.getIsbn())) {
            System.out.println("The book is already available!");
            return; 
        }
        }
        books.add(book);
        System.out.println("The book has been successfully added: " + book.getTitle());   
}
    
    public boolean removeBook(String isbn) {
        for (Book b : books) {
        if (b.getIsbn().equals(isbn)) {
            books.remove(b);
            System.out.println("The book has been successfully removed: " + b.getTitle());   
            return true;
        }
        }
           System.out.println("The book is not available!");
            return false;
        }
        
    public Book searchByTitle(String title) {
        for (Book b : books) {
        if (b.getTitle().equals(title)) {
            System.out.println(b + " found!");
            return b;
        }
        }
            System.out.println("Not found!");
            return null;
    }
    
    public Book searchByAuthor(String author) {
        for (Book b : books) {
        if (b.getAuthor().equals(author)) {
            System.out.println(b + " found!");
            return b;
        }
        }
            System.out.println("Not found!");
            return null;
    }
    
    public void displayAllBooks() {
        for (Book b : books) {
            System.out.println(b);
        }
}
    
    public boolean borrowBook(String isbn, Member member) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                if (!b.isAvailable()) {
                    System.out.print("The book is currently borrowed!");
                    return false;
                }   
                member.getBorrowedBooks().add(b);
                b.setAvailable(false);
                System.out.println(member.getName() + " borrowed: " + b.getTitle());
                return true;       
            }
       }
        System.out.println("Book not found!");
        return false;            
    }
    
    public boolean returnBook(String isbn, Member member) {
        for (Book b : member.getBorrowedBooks()) {
            if (b.getIsbn().equals(isbn)) {
                member.getBorrowedBooks().remove(b);
                b.setAvailable(true);
                System.out.println(member.getName() + " return: " + b.getTitle());
                return true;  
            }
        }
        System.out.println("Book not found!");
        return false;            
    }
    

    public void saveToFile(String filename) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            for (Book b : books) {
                writer.println(b.getTitle() + ", " + b.getAuthor() + ", " + b.getIsbn() + ", " + b.isAvailable());
            }
            writer.close();
            System.out.println("Books saved to file successfully!");
        }   catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
        
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
            
                String title = parts[0];
                String author = parts[1];
                String isbn = parts[2];
                boolean available = Boolean.parseBoolean(parts[3]);
            
                Book book = new Book(title, author, isbn);
                book.setAvailable(available);
                books.add(book);
            }
        
            reader.close();
            System.out.println("Books loaded from file successfully!");
        
        }   catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
            }
    }
    
}
