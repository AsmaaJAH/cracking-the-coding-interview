/*
7.5 Online Book Reader: Design the data structures for an online book reader system.
*/

package ch7_oop_design;
import java.util.*;


public class OnlineBookReader5 {

    public static class Book {
        private int id;
        private String title;
        private String author;
        private List<String> pages;

        public Book(int id, String title, String author, List<String> pages) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.pages = pages;
        }

        public String getPage(int pageNumber) {
            if (pageNumber >= 0 && pageNumber < pages.size()) {
                return pages.get(pageNumber);
            }
            return "Invalid page number.";
        }

        public int getTotalPages() {
            return pages.size();
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }
    }

    public static class User {
        private int userId;
        private String name;
        private Map<Integer, Integer> bookmarks = new HashMap<>(); // bookId -> page where you have stopped reading

        public User(int userId, String name) {
            this.userId = userId;
            this.name = name;
        }

        public void bookmarkPage(int bookId, int page) {
            bookmarks.put(bookId, page);
        }

        public int getBookmarkedPage(int bookId) {
            return bookmarks.getOrDefault(bookId, 0); //0 if the user didn't read the book before 
        }

        public String getName() {
            return name;
        }
    }

    public static class Library {
        private Map<Integer, Book> books = new HashMap<>();

        public void addBook(Book book) {
            books.put(book.getId(), book);
        }

        public Book getBook(int id) {
            return books.get(id);
        }

        public Collection<Book> getAllBooks() {
            return books.values();
        }
    }

    public static class Display {
        private Book currentBook;
        private int currentPage;

        public void displayPage() {
            if (currentBook != null) {
                System.out.println("Page " + currentPage + ": " + currentBook.getPage(currentPage));
            } else {
                System.out.println("No book is currently open.");
            }
        }

        public void openBook(Book book, int page) {
            this.currentBook = book;
            this.currentPage = page;
            displayPage();
        }

        public void nextPage() {
            if (currentBook != null && currentPage < currentBook.getTotalPages() - 1) {
                currentPage++;
                displayPage();
            } else {
                System.out.println("You're at the end of the book.");
            }
        }

        public void previousPage() {
            if (currentPage > 0) {
                currentPage--;
                displayPage();
            } else {
                System.out.println("You're at the beginning.");
            }
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public Book getCurrentBook() {
            return currentBook;
        }
    }

    public static class OnlineBookReaderSystem {
        private Library library;
        private Display display;
        private User currentUser;

        public OnlineBookReaderSystem() {
            library = new Library();
            display = new Display();
        }

        public void login(User user) {
            this.currentUser = user;
            System.out.println("Welcome, " + user.getName());
        }

        public void openBook(int bookId) {
            Book book = library.getBook(bookId);
            if (book != null) {
                int bookmarkedPage = currentUser.getBookmarkedPage(bookId);
                display.openBook(book, bookmarkedPage);
            } else {
                System.out.println("Book not found.");
            }
        }

        public void nextPage() {
            display.nextPage();
        }

        public void previousPage() {
            display.previousPage();
        }

        public void bookmarkCurrentPage() {
            if (display.getCurrentBook() != null) {
                currentUser.bookmarkPage(display.getCurrentBook().getId(), display.getCurrentPage());
                System.out.println("Bookmarked page " + display.getCurrentPage());
            }
        }

        public void addBook(Book book) {
            library.addBook(book);
        }
    }
}

