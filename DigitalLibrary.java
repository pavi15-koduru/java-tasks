import java.util.*;
class Book {
    int id;
    String title;
    boolean isIssued = false;

    Book(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return id + ": " + title + (isIssued ? " [Issued]" : " [Available]");
    }
}

public class DigitalLibrary {
    static Scanner scanner = new Scanner(System.in);
    static Map<Integer, Book> books = new HashMap<>();
    static int bookIdCounter = 1;

    public static void main(String[] args) {
        System.out.println("Welcome to the Digital Library Management System!");
        System.out.print("Login as (admin/user): ");
        String role = scanner.nextLine().trim().toLowerCase();

        if (role.equals("admin")) {
            adminPanel();
        } else if (role.equals("user")) {
            userPanel();
        } else {
            System.out.println("Invalid role. Exiting.");
        }
    }

    // ================= Admin Functions ===================
    static void adminPanel() {
        while (true) {
            System.out.println("\n=== Admin Panel ===");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. View All Books");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addBook();
                case 2 -> deleteBook();
                case 3 -> viewBooks();
                case 4 -> {
                    System.out.println("Logging out from Admin Panel.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        Book book = new Book(bookIdCounter++, title);
        books.put(book.id, book);
        System.out.println("Book added: " + book);
    }

    static void deleteBook() {
        System.out.print("Enter book ID to delete: ");
        int id = scanner.nextInt();
        if (books.remove(id) != null) {
            System.out.println("Book deleted.");
        } else {
            System.out.println("Book not found.");
        }
    }

    // ================= User Functions ===================
    static void userPanel() {
        while (true) {
            System.out.println("\n=== User Panel ===");
            System.out.println("1. View Books");
            System.out.println("2. Search Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> viewBooks();
                case 2 -> searchBook();
                case 3 -> issueBook();
                case 4 -> returnBook();
                case 5 -> {
                    System.out.println("Logging out from User Panel.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void viewBooks() {
        System.out.println("\nAvailable Books:");
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            books.values().forEach(System.out::println);
        }
    }

    static void searchBook() {
        System.out.print("Enter title to search: ");
        String keyword = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (Book book : books.values()) {
            if (book.title.toLowerCase().contains(keyword)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) System.out.println("No books found with that title.");
    }

    static void issueBook() {
        System.out.print("Enter book ID to issue: ");
        int id = scanner.nextInt();
        Book book = books.get(id);
        if (book == null) {
            System.out.println("Book not found.");
        } else if (book.isIssued) {
            System.out.println("Book is already issued.");
        } else {
            book.isIssued = true;
            System.out.println("Book issued: " + book.title);
        }
    }

    static void returnBook() {
        System.out.print("Enter book ID to return: ");
        int id = scanner.nextInt();
        Book book = books.get(id);
        if (book == null || !book.isIssued) {
            System.out.println("Invalid return. Either the book doesn't exist or wasn't issued.");
        } else {
            book.isIssued = false;
            System.out.println("Book returned: " + book.title);
        }
    }
}