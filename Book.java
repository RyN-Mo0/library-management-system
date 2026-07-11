package com.mycompany.library;

import java.time.LocalDate;

public class Book {
@Override
public String toString() {
    return "Title: " + title + " | Author: " + author + " | ISBN: " + ISBN + " | Available: " + available +
           "\n   AI Description: " + description;
}
    private final String title;
    private final String author;
    private final String ISBN;
    private String description;
    private boolean available;
    private String borrowedBy;
    private LocalDate dueDate;

    
    public Book(String title, String author, String isbn) {
    this.title = title;
    this.author = author;
    this.ISBN = isbn;
    this.available = true;
}
    
    public String getTitle(){
        return title;
     }
         
    public String getIsbn(){
        return ISBN;
    } 
    
    public String getAuthor(){
        return author;
    }
    
    public boolean isAvailable(){
        return available;
    }    
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(String borrowedBy) {
        this.borrowedBy = borrowedBy;
    }
    
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }   
}
