package com.mycompany.library;

public class Main {
    public static void main(String[] args) {
        
        Library library = new Library();
        Member ahmed = new Member("Ahmed", "M001");

        // ===== 1. إضافة كتب =====
        System.out.println("===== Adding Books =====");
        library.addBook(new Book("Harry Potter", "J.K. Rowling", "111"));
        library.addBook(new Book("Clean Code", "Robert Martin", "222"));
        library.addBook(new Book("1984", "George Orwell", "333"));
        library.addBook(new Book("Harry Potter Copy", "J.K. Rowling", "111")); // تكرار متعمد

        // ===== 2. عرض كل الكتب =====
        System.out.println("\n===== All Books =====");
        library.displayAllBooks();

        // ===== 3. البحث =====
        System.out.println("\n===== Search =====");
        library.searchByTitle("Clean Code");
        library.searchByTitle("Not Existing Book");

        // ===== 4. الاستعارة والإرجاع =====
        System.out.println("\n===== Borrow & Return =====");
        library.borrowBook("111", ahmed);
        library.borrowBook("111", ahmed); // محاولة ثانية، لازم تفشل
        library.returnBook("111", ahmed);
        library.borrowBook("111", ahmed); // بعد الإرجاع، لازم تنجح

        // ===== 5. الحذف =====
        System.out.println("\n===== Remove =====");
        library.removeBook("333");
        library.displayAllBooks();

        // ===== 6. حفظ البيانات لملف =====
        System.out.println("\n===== Save to File =====");
        library.saveToFile("books.txt");

        // ===== 7. تحميل البيانات من ملف (محاكاة إعادة تشغيل البرنامج) =====
        System.out.println("\n===== Load from File (New Library Instance) =====");
        Library newLibrarySession = new Library();
        newLibrarySession.loadFromFile("books.txt");
        newLibrarySession.displayAllBooks();
    }
}