package io;

import enums.Genre;
import model.Book;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookLoader {

    private static final Logger LOGGER = Logger.getLogger(BookLoader.class.getName());

    public static List<Book> loadBooksFromFile(String filename) {
        List<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    String publisher = parts[2].trim();
                    int year = Integer.parseInt(parts[3].trim());
                    int pages = Integer.parseInt(parts[4].trim());
                    Genre genre = Genre.valueOf(parts[5].trim());
                    String isbn = parts[6].trim();
                    boolean favourite = Boolean.parseBoolean(parts[7].trim());

                    Book book = new Book(title, author, publisher, year, pages, genre, isbn, favourite);
                    books.add(book);
                } else {
                    LOGGER.log(Level.WARNING, "Invalid line in file: " + line);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, "Error reading file: " + filename, e);
        }

        return books;
    }
}