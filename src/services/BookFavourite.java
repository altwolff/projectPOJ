package services;

import model.Book;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookFavourite {

    private static final Logger LOGGER = Logger.getLogger(BookFavourite.class.getName());

    public static void showFavourites(List<Book> books, Scanner sc, String filename) {
        List<Book> favouriteBooks = getFavouriteBooks(books);

        if (favouriteBooks.isEmpty()) {
            System.out.println("No books marked as favourites.");
            return;
        }

        BookTablePrinter.printBooks(favouriteBooks);

        System.out.print("Do you want to remove any books from favourites? (yes/no): ");
        String response = sc.next().toLowerCase();
        sc.nextLine();

        if (response.equals("yes")) {
            System.out.print("Enter the index of the book to remove from favourites: ");
            int index = sc.nextInt();
            sc.nextLine();

            if (index > 0 && index <= favouriteBooks.size()) {
                Book selectedBook = favouriteBooks.get(index - 1);
                selectedBook.setFavourite(false);

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
                    for (Book book : books) {
                        writeBookToFile(bw, book);
                    }
                    System.out.println("Book removed from favourites successfully.");
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error writing to file: " + filename, e);
                }
            } else {
                System.err.println("Invalid index. No changes were made.");
            }
        }
    }

    private static List<Book> getFavouriteBooks(List<Book> books) {
        return books.stream()
                .filter(Book::isFavourite)
                .toList();
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
