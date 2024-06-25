package services;

import enums.SearchField;
import exceptions.InvalidSearchFieldException;
import model.Book;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookSearch {

    private static final Logger LOGGER = Logger.getLogger(BookSearch.class.getName());

    public static void searchBook(Scanner sc, List<Book> books, String filename) {
        SearchField searchField = null;

        while (searchField == null) {
            System.out.print("What would you like to search? (title, author, publisher, year, pages, genre, isbn): ");
            String searchFieldInput = sc.next().toLowerCase();
            sc.nextLine();

            try {
                searchField = SearchField.fromString(searchFieldInput);
            } catch (InvalidSearchFieldException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.print("Enter the search term: ");
        String searchTerm = sc.nextLine().trim().toLowerCase();

        List<Book> searchResults = searchBooks(books, searchField, searchTerm);

        if (searchResults.isEmpty()) {
            System.out.println("No books found matching the search term.");
        } else {
            BookTablePrinter.printBooks(searchResults);
            System.out.print("Do you want to add any of these books to your favourites? (yes/no): ");
            String response = sc.next().toLowerCase();
            sc.nextLine();

            if (response.equals("yes")) {
                System.out.print("Enter the index of the book to add to favourites: ");
                int index = sc.nextInt();
                sc.nextLine();

                if (index > 0 && index <= searchResults.size()) {
                    Book selectedBook = searchResults.get(index - 1);
                    selectedBook.setFavourite(true);

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
                        for (Book book : books) {
                            writeBookToFile(bw, book);
                        }
                        System.out.println("Book marked as favourite.");
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Error writing to file: " + filename, e);
                    }
                } else {
                    System.err.println("Invalid index. No changes were made.");
                }
            }
        }
    }

    private static List<Book> searchBooks(List<Book> books, SearchField searchField, String searchTerm) {
        List<Book> searchResults = new ArrayList<>();

        for (Book book : books) {
            switch (searchField) {
                case TITLE:
                    if (book.getTitle().toLowerCase().contains(searchTerm)) {
                        searchResults.add(book);
                    }
                    break;
                case AUTHOR:
                    if (book.getAuthor().toLowerCase().contains(searchTerm)) {
                        searchResults.add(book);
                    }
                    break;
                case PUBLISHER:
                    if (book.getPublisher().toLowerCase().contains(searchTerm)) {
                        searchResults.add(book);
                    }
                    break;
                case YEAR:
                    if (String.valueOf(book.getYear()).equals(searchTerm)) {
                        searchResults.add(book);
                    }
                    break;
                case PAGES:
                    if (String.valueOf(book.getPages()).equals(searchTerm)) {
                        searchResults.add(book);
                    }
                    break;
                case GENRE:
                    if (book.getGenre().toString().toLowerCase().contains(searchTerm)) {
                        searchResults.add(book);
                    }
                    break;
                case ISBN:
                    if (book.getIsbn().toLowerCase().contains(searchTerm)) {
                        searchResults.add(book);
                    }
                    break;
                default:
                    break;
            }
        }

        return searchResults;
    }

    private static void writeBookToFile(BufferedWriter bw, Book book) throws IOException {
        bw.write(book.getTitle() + "," +
                book.getAuthor() + "," +
                book.getPublisher() + "," +
                book.getYear() + "," +
                book.getPages() + "," +
                book.getGenre() + "," +
                book.getIsbn() + "," +
                book.isFavourite());
        bw.newLine();
    }
}