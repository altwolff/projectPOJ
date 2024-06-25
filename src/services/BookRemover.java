package services;

import model.Book;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookRemover {

    private static final Logger LOGGER = Logger.getLogger(BookRemover.class.getName());

    public static void removeBook(Scanner sc, List<Book> books, String filename) {
        System.out.print("Enter the index of the book to remove (1-" + books.size() + "): ");
        int index = readIndexInput(sc, books.size());

        Book removedBook = books.remove(index - 1);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Book book : books) {
                writeBookToFile(bw, book);
            }
            System.out.println("Book removed successfully: " + removedBook.getTitle() + " by " + removedBook.getAuthor());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing to file: " + filename, e);
        }
    }

    private static int readIndexInput(Scanner sc, int maxIndex) {
        int input;
        do {
            try {
                input = sc.nextInt();
                sc.nextLine();
                if (input < 1 || input > maxIndex) {
                    System.err.println("Invalid index. Please enter a number between 1 and " + maxIndex + ".");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.err.println("Invalid input. Please enter a valid number.");
                sc.nextLine();
            }
        } while (true);
        return input;
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