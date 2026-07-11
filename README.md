# Library Management System

A console-based Library Management System built in Java, demonstrating Object-Oriented Programming, AI API integration, file persistence, and role-based access control.

## Features

### Book Management (CRUD)
- Add new books with duplicate ISBN prevention
- Remove books by ISBN
- Search books by title or author
- Display all books, sorted by title or author

### AI-Powered Descriptions
- Automatically generates a book description using the **Google Gemini API** whenever a new book is added
- Descriptions are generated in real time and stored alongside the book's data

### Role-Based Access Control
- Two user roles: **Admin** and **Member**, implemented via Java inheritance (`Admin extends Member`)
- Only Admins can add or remove books; both roles can borrow, return, and browse books
- Access checks are enforced using `instanceof` at the service layer

### Authentication
- Persistent login system with Member ID and password
- New users can register; returning users log in with saved credentials
- User accounts are stored in a separate data file, decoupled from book data

### Borrowing System with Due Dates & Fines
- Borrowing a book sets a 14-day due date automatically (`java.time.LocalDate`)
- Returning a book calculates a late fine automatically based on days overdue (`ChronoUnit`)
- A member's borrowed books are automatically restored after login, even across sessions

### Data Persistence
- Book catalog is saved and loaded using **JSON** (via Gson), avoiding the pitfalls of naive CSV parsing when text fields contain commas
- A custom `TypeAdapter` handles serialization of `LocalDate` fields
- Data survives across program restarts

## Built With

- **Java 17+** (Core OOP, Collections, `java.time`)
- **Google Gemini API** for AI-generated content
- **Gson** for JSON serialization/deserialization
- **Maven** for dependency management
- Standard Java I/O and `java.net.http.HttpClient` for external API calls

## Project Structure

```
src/main/java/com/mycompany/library/
├── Book.java              # Book entity: title, author, ISBN, description, due date, etc.
├── Member.java            # Base user class: name, ID, password, borrowed books
├── Admin.java             # Privileged user, extends Member
├── Library.java           # Core logic: CRUD, borrowing, sorting, file I/O
├── UserManager.java       # User registration, login, and persistence
├── GeminiClient.java      # Handles communication with the Gemini API
├── LocalDateAdapter.java  # Custom Gson adapter for LocalDate serialization
└── Main.java              # Console menu and program entry point
```

## Key Concepts Demonstrated

- Object-Oriented Design: encapsulation, inheritance, polymorphism
- External API integration (HTTP requests, JSON parsing, error handling)
- File-based persistence with JSON and custom type adapters
- Defensive programming: duplicate checks, null handling, input validation
- Role-based access control patterns
- Working with dates and time calculations in Java

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/YOUR_USERNAME/library-management-system.git
   ```
2. Set your Gemini API key as an environment variable:
   ```bash
   setx GEMINI_API_KEY "your-api-key-here"     # Windows
   export GEMINI_API_KEY="your-api-key-here"   # macOS/Linux
   ```
   Get a free API key at [ai.google.dev](https://ai.google.dev).
3. Open the project in your Java IDE (NetBeans, IntelliJ, Eclipse) as a Maven project.
4. Run `Main.java`.

## 📖 Sample Interaction

```
=== Welcome to the Library System ===
1. Login (existing member)
2. Sign in (new member)
Choose: 1
Enter your Member ID: ryn00
Enter your password: ****
Welcome back, Rayan!

===== Menu =====
1. Add Book
2. Remove Book
3. Search by Title
4. Search by Author
5. Display All Books
6. Borrow Book
7. Return Book
8. My Borrowed Books
9. Sort by Title
10. Sort by Author
11. Save to File
0. Exit
```

## Known Limitations (Future Improvements)

- User passwords are stored in plain text, which is not secure practice for production systems. A hashing mechanism (e.g. bcrypt) would be a natural next step.
- Admin registration requires a secret code (stored as an environment variable, not in source code) to prevent unrestricted self-registration as Admin. This is a lightweight safeguard suitable for a learning project — a production system would use a proper invitation-based or database-driven approval process instead.
- No graphical interface yet — a JavaFX or Swing front-end is a planned future enhancement.
- No automated tests yet (JUnit) — planned as a future addition.

## Future Improvements

- [ ] Graphical User Interface (JavaFX/Swing)
- [ ] Password hashing for stored credentials
- [ ] Admin approval flow for new admin registrations
- [ ] Unit tests with JUnit
- [ ] Book categories and filtering
- [ ] Database integration (replacing file-based storage)

## Author

Built as a self-directed learning project to strengthen Java fundamentals, OOP design, and real-world integration skills (external APIs, persistence, authentication).

---

*This project evolved iteratively — starting as a simple console CRUD app and growing into a multi-feature system with AI integration, role-based access, and robust data persistence.*
