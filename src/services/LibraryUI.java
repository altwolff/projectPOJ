package services;

import io.BookLoader;
import model.Book;
import services.*;

import java.util.List;
import java.util.Scanner;

public class LibraryUI {
    private static final String FILE_PATH = "resources/books.txt";

    public static void runLibraryUI() {
        Scanner sc = new Scanner(System.in);
        System.out.println(WelcomeSign.getSign());

        boolean exit = false;
        List<Book> books = BookLoader.loadBooksFromFile(FILE_PATH);

        while (!exit) {
            int choice = ChoiceTable.getUserChoice(sc);

            switch (choice) {
                case 1:
                    BookAdder.addBook(sc, books, FILE_PATH);
                    break;
                case 2:
                    BookRemover.removeBook(sc, books, FILE_PATH);
                    break;
                case 3:
                    BookSearch.searchBook(sc, books, FILE_PATH);
                    break;
                case 4:
                    BookTablePrinter.printBooks(books);
                    break;
                case 5:
                    BookFavourite.showFavourites(books, sc, FILE_PATH);
                    break;
                case 6:
                    System.out.println("Shutting down... Goodbye.");
                    exit = true;
                    break;
                default:
                    System.err.println("Unexpected error occurred.");
                    break;
            }
        }

        sc.close();
    }
}
