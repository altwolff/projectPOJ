package services;

import model.Book;

import java.util.List;

public class BookTablePrinter {

    public static void printBooks(List<Book> books) {

        int maxIndexLength = String.valueOf(books.size()).length();
        int indexHeaderLength = Math.max(maxIndexLength, "Index".length());


        int maxTitleLength = "Title".length();
        int maxAuthorLength = "Author".length();
        int maxPublisherLength = "Publisher".length();
        int maxYearLength = "Year".length();
        int maxPagesLength = "Pages".length();
        int maxGenreLength = "enums.Genre".length();
        int maxIsbnLength = "ISBN".length();

        for (Book book : books) {
            if (book.getTitle().length() > maxTitleLength) maxTitleLength = book.getTitle().length();
            if (book.getAuthor().length() > maxAuthorLength) maxAuthorLength = book.getAuthor().length();
            if (book.getPublisher().length() > maxPublisherLength) maxPublisherLength = book.getPublisher().length();
            if (String.valueOf(book.getYear()).length() > maxYearLength)
                maxYearLength = String.valueOf(book.getYear()).length();
            if (String.valueOf(book.getPages()).length() > maxPagesLength)
                maxPagesLength = String.valueOf(book.getPages()).length();
            if (book.getGenre().toString().length() > maxGenreLength)
                maxGenreLength = book.getGenre().toString().length();
            if (book.getIsbn().length() > maxIsbnLength) maxIsbnLength = book.getIsbn().length();
        }

        String headerFormat = "+-" + "-".repeat(indexHeaderLength) + "-+-" +
                "-".repeat(maxTitleLength) + "-+-" +
                "-".repeat(maxAuthorLength) + "-+-" +
                "-".repeat(maxPublisherLength) + "-+-" +
                "-".repeat(maxYearLength) + "-+-" +
                "-".repeat(maxPagesLength) + "-+-" +
                "-".repeat(maxGenreLength) + "-+-" +
                "-".repeat(maxIsbnLength) + "-+";

        String rowFormat = "| %-" + indexHeaderLength + "s | %-" + maxTitleLength + "s | %-" + maxAuthorLength + "s | %-" + maxPublisherLength + "s | %-" + maxYearLength + "s | %-" + maxPagesLength + "s | %-" + maxGenreLength + "s | %-" + maxIsbnLength + "s |\n";

        System.out.println(headerFormat);
        System.out.printf(rowFormat,
                "Index", "Title", "Author", "Publisher", "Year", "Pages", "Genre", "ISBN");
        System.out.println(headerFormat);

        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.printf(rowFormat,
                    (i + 1),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getYear(),
                    book.getPages(),
                    book.getGenre(),
                    book.getIsbn()
            );
            System.out.println(headerFormat);
        }
        System.out.println();
    }
}