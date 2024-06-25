package services;

import enums.Genre;
import io.InputUtils;
import model.Book;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookAdder {

    private static final Logger LOGGER = Logger.getLogger(BookAdder.class.getName());

    public static void addBook(Scanner sc, List<Book> books, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            sc.nextLine();

            System.out.println("Enter the details of the book:");

            String title = InputUtils.readStringInput(sc, "Title");
            String author = InputUtils.readStringInput(sc, "Author");
            String publisher = InputUtils.readStringInput(sc, "Publisher");
            int year = InputUtils.readYearInput(sc);
            int pages = InputUtils.readPagesInput(sc);
            Genre genre = InputUtils.readGenreInput(sc);
            String isbn = InputUtils.readISBNInput(sc);
            boolean favourite = InputUtils.readBooleanInput(sc);

            Book newBook = new Book(title, author, publisher, year, pages, genre, isbn, favourite);
            books.add(newBook);

            bw.write(newBook.getTitle() + "," +
                    newBook.getAuthor() + "," +
                    newBook.getPublisher() + "," +
                    newBook.getYear() + "," +
                    newBook.getPages() + "," +
                    newBook.getGenre() + "," +
                    newBook.getIsbn() + "," +
                    newBook.isFavourite());
            bw.newLine();
            bw.flush();

            System.out.println("Book added successfully.");

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing to file: " + filename, e);
        }
    }
}
