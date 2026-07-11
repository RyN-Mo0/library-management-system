package com.mycompany.library;

import java.util.ArrayList;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;



public class Library {    
    private final ArrayList<Book> books;
    
    public Library() {
        books = new ArrayList<>();
    }
    
    
    public void addBook(Book book, Member requester) {
        if (!(requester instanceof Admin)) {
            System.out.println("Access denied: Only admins can add books.");
            return;
        }
        for (Book b : books) {
            if (b.getIsbn().equals(book.getIsbn())) {
                System.out.println("The book is already available!");
                return; 
            }
        }
        System.out.println("Generating AI description...");
        String description = GeminiClient.generateBookDescription(book.getTitle(), book.getAuthor());
        book.setDescription(description);
        books.add(book);
        System.out.println("The book has been successfully added: " + book.getTitle());   
}
    
    public boolean removeBook(String isbn, Member requester) {
        if (!(requester instanceof Admin)) {
            System.out.println("Access denied: Only admins can remove books.");
            return false;
    }
            
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
    
    public void sortByTitle() {
        books.sort(Comparator.comparing(Book::getTitle));
        System.out.println("Books sorted by title.");
    }
    
    public void sortByAuthor() {
        books.sort(Comparator.comparing(Book::getAuthor));
        System.out.println("Books sorted by author.");
    }
        
    public Book searchByTitle(String title) {
        for (Book b : books) {
        if (b.getTitle().equals(title)) {
            System.out.println(b + "\n Found!");
            return b;
        }
        }
            System.out.println(title + " " + "Not Found!");
            return null;
    }
    
    public Book searchByAuthor(String author) {
        for (Book b : books) {
        if (b.getAuthor().equals(author)) {
            System.out.println(b + "\n Found!");
            return b;
        }
        }
            System.out.println(author + " " + "Not Found!");
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
                b.setBorrowedBy(member.getMemberId());  
                b.setDueDate(LocalDate.now().plusDays(14));
                System.out.println(member.getName() + " borrowed: " + b.getTitle() + " | Due: " + b.getDueDate());
                return true;       
            }
       }
        System.out.println("Book not found!");
        return false;            
    }
    
    public boolean returnBook(String isbn, Member member) {
        for (Book b : member.getBorrowedBooks()) {
            if (b.getIsbn().equals(isbn)) {
                
                LocalDate today = LocalDate.now();
                long daysLate = ChronoUnit.DAYS.between(b.getDueDate(), today);
                
                if (daysLate > 0){
                    double fine = daysLate * 0.5;
                    System.out.println("Book returned late by " + daysLate + " day(s). Fine: " + fine + " SAR");
                }
                else {
                    System.out.println("Book returned on time. No fine.");

                }
                
                member.getBorrowedBooks().remove(b);
                b.setAvailable(true);
                b.setDueDate(null);
                b.setBorrowedBy(null);
                System.out.println(member.getName() + " return: " + b.getTitle() + " in " + today);
                return true;  
            }   
        }
        System.out.println("Book not found!");
        return false;            
    }
    
    public void restoreBorrowedBooks(Member member) {
        for (Book b : books) {
            if (b.getBorrowedBy() != null && b.getBorrowedBy().equals(member.getMemberId())) {
                member.getBorrowedBooks().add(b);
            }
        }
    }
    
    public void displayBorrowedBooks(Member member) {
        if (member.getBorrowedBooks().isEmpty()) {
            System.out.println("You have no borrowed books.");
            return;
        }
        System.out.println("--- Your Borrowed Books ---");
        for (Book b : member.getBorrowedBooks()) {
            System.out.println(b + " | Due: " + b.getDueDate());
        }
    }   
    


public void saveToFile(String filename) {
    try {
Gson gson = new GsonBuilder()
    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
    .setPrettyPrinting()
    .create();
        String json = gson.toJson(books);
        
        PrintWriter writer = new PrintWriter(new FileWriter(filename));
        writer.write(json);
        writer.close();
        System.out.println("Books saved to file successfully!");
    } catch (IOException e) {
        System.out.println("Error saving file: " + e.getMessage());
    }
}

public void loadFromFile(String filename) {
    try {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
Gson gson = new GsonBuilder()
    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
    .create();
        
        Type bookListType = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> loadedBooks = gson.fromJson(reader, bookListType);
        
        if (loadedBooks != null) {
            books.addAll(loadedBooks);
        }
        
        reader.close();
        System.out.println("Books loaded from file successfully!");
        
    } catch (IOException e) {
        System.out.println("No existing file found. Starting fresh.");
    }
}
    
}
