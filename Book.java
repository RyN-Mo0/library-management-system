
package com.mycompany.library;

public class Book {
        @Override
public String toString() {
    return "Title: " + title + " | Author: " + author + " | ISBN: " + ISBN + " | Available: " + Available;
}
    private String title;
    private String author;
    private String ISBN;
    private boolean Available;

    
    public Book(String title, String author, String isbn) {
    this.title = title;
    this.author = author;
    this.ISBN = isbn;
    this.Available = true;
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
        return Available;
    }    
    
    public void setAvailable(boolean available) {
    this.Available = available;
    }

}
