package com.mycompany.library;

import java.io.*;
import java.util.regex.*;
import java.util.Scanner;

public class UserManager {


    private static final String FILE_NAME = "users.txt";

    public static Member findUser(String memberId) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\|\\| ");  
                String savedId = parts[1];
                
                if (savedId.equals(memberId)) {
                    String name = parts[0];
                    String password = parts[2];
                    String role = parts[3];
                    reader.close();
                    
                    if (role.equals("ADMIN")) {
                        return new Admin(name, savedId, password);
                    } else {
                        return new Member(name, savedId, password);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("No existing users file found. Starting fresh.");
        }
        return null; 
    }
    
    
    public static void saveUser(Member member, boolean isAdmin) {
        try {
            FileWriter writer = new FileWriter(FILE_NAME, true); // true = append mode
            String role = isAdmin ? "ADMIN" : "MEMBER";
            writer.write(member.getName() + " || " + member.getMemberId() + " || " + member.getPassword() + " || " + role + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }    
    
    public Member login(Scanner scanner) {
        System.out.print("Enter your Member ID: ");
        String id = scanner.nextLine();
        Member currentUser = UserManager.findUser(id);
    
        if (currentUser == null) {
            System.out.println("User not found.");
            return null;
        }
    
        System.out.print("Enter your password: ");
        String enteredPassword = scanner.nextLine();

        if (!currentUser.getPassword().equals(enteredPassword)) {
            System.out.println("Incorrect password.");
            return null;
        }
    
        System.out.println("\nWelcome back, " + currentUser.getName() + "!");
        return currentUser;   
    }

    private static final String ADMIN_SECRET = System.getenv("ADMIN_SECRET_CODE");

    public Member signIn(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
    
        System.out.print("Enter your Member ID: ");
        String id = scanner.nextLine();
        
        if (UserManager.findUser(id) != null) {
            System.out.println("This ID is already registered. Please login instead.");
            return null;
        }
    
        System.out.print("Create a password: ");
        String password = scanner.nextLine();
    
        System.out.println("1. Register as Admin");
        System.out.println("2. Register as Member");
        System.out.print("Choose: ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine();
    
        boolean isAdmin = false;
    
        if (roleChoice == 1) {
            System.out.print("Enter Admin Secret Code: ");
            String secretCode = scanner.nextLine();
        
        if (ADMIN_SECRET != null && secretCode.equals(ADMIN_SECRET)) {
            isAdmin = true;
        } else {
            System.out.println("Incorrect code. Registering as regular Member instead.");
        }
    }
    
    Member newUser;
    
    if (isAdmin) {
        newUser = new Admin(name, id, password);
    } else {
        newUser = new Member(name, id, password);
    }

    UserManager.saveUser(newUser, isAdmin);
    System.out.println("Account created successfully as " + (isAdmin ? "Admin" : "Member") + "!");

    return newUser;
}
}