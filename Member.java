package com.mycompany.library;

import java.util.ArrayList;

public class Member {
    protected String name;
    protected String memberId;
    protected String password;
    protected final ArrayList<Book> borrowedBooks;


    public Member(String name, String memberId, String password) {
        this.name = name;
        this.memberId = memberId;
        this.password = password;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getMemberId() {
        return memberId;
    }
    
    public String getPassword() {
        return password;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
