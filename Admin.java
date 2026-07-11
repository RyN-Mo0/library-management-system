package com.mycompany.library;

public class Admin extends Member {
    
    public Admin(String name, String memberId, String password) {
        super(name, memberId, password);
    }
   
    public boolean isAdmin() {
        return true;
    }    
}
