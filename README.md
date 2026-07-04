#  Library Management System

A console-based Library Management System built in Java, demonstrating core Object-Oriented Programming principles, collections handling, and file persistence.

##  Features

- **Book Management (CRUD)**
  - Add new books (with duplicate ISBN prevention)
  - Remove books by ISBN
  - Search books by title or author
  - Display all books in the library

- **Member & Borrowing System**
  - Register library members
  - Borrow books (with availability checks)
  - Return books
  - Track which books each member currently holds

- **Data Persistence**
  - Save the entire library catalog to a text file
  - Load the catalog back on startup — no data loss between sessions

##  Built With

- **Java** (Core OOP concepts)
- Standard Java I/O (`BufferedReader`, `PrintWriter`) for file persistence
- `ArrayList` for in-memory data management

##  Project Structure

```
src/
└── com/mycompany/library/
    ├── Book.java       # Represents a single book (title, author, ISBN, availability)
    ├── Member.java     # Represents a library member and their borrowed books
    ├── Library.java    # Core logic: CRUD, borrowing/returning, file I/O
    └── Main.java       # Entry point / demo runner
```

##  Key Concepts Demonstrated

- Object-Oriented Design (encapsulation, class relationships)
- Collections (`ArrayList`) for dynamic data storage
- Exception handling (`try/catch` for file operations)
- File I/O for data persistence (read/write text files)
- Defensive programming (duplicate checks, null handling, availability validation)

##  How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/YOUR_USERNAME/library-management-system.git
   ```
2. Open the project in your preferred Java IDE (NetBeans, IntelliJ, Eclipse).
3. Run `Main.java`.

##  Sample Output

```
The book has been successfully added: Harry Potter
--- Borrow ---
Ahmed borrowed: Harry Potter
--- Try Borrow Again (should fail, already borrowed) ---
The book is currently unavailable!
--- Return ---
Ahmed removed: Harry Potter
--- Try Borrow After Return (should succeed now) ---
Ahmed borrowed: Harry Potter
```

##  Future Improvements

- [ ] Graphical User Interface (JavaFX/Swing)
- [ ] Due dates and overdue fine calculation
- [ ] Multiple member support with unique IDs and login
- [ ] Database integration (replacing text file storage)

##  Author

Built as a learning project to strengthen Java fundamentals and OOP design.

---

*This project was built as part of a self-directed learning journey in Computer Science.*
