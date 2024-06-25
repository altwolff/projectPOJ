package io;

import enums.Genre;

import java.time.Year;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputUtils {
    private static final Pattern ISBN_PATTERN = Pattern.compile("\\d{3}[-\\s]?\\d[-\\s]?\\d{2}[-\\s]?\\d{6}[-\\s]?\\d");

    public static String readStringInput(Scanner sc, String prompt) {
        String input;
        do {
            System.out.print(prompt + ": ");
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.err.println(prompt + " cannot be empty. Please retry.");
            } else {
                break;
            }
        } while (true);
        return input;
    }

    public static int readPagesInput(Scanner sc) {
        int input;
        do {
            System.out.print("Pages: ");
            try {
                input = sc.nextInt();
                sc.nextLine();
                if (input < 1) {
                    System.err.println("Pages must be at least 1. Please retry.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.err.println("Invalid pages format. Please retry.");
                sc.nextLine();
            }
        } while (true);
        return input;
    }

    public static int readYearInput(Scanner sc) {
        int input;
        do {
            System.out.print("Year: ");
            try {
                input = sc.nextInt();
                sc.nextLine();
                if (input < 0 || input > Year.now().getValue()) {
                    System.err.println("Year must be between 0 and current year (" + Year.now().getValue() + "). Please retry.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.err.println("Invalid year format. Please retry.");
                sc.nextLine();
            }
        } while (true);
        return input;
    }

    public static Genre readGenreInput(Scanner sc) {
        Genre genre;
        do {
            System.out.print("Genre (" + getGenres() + "): ");
            try {
                genre = Genre.valueOf(sc.nextLine().trim().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid genre. Please retry.");
            }
        } while (true);
        return genre;
    }

    public static String readISBNInput(Scanner sc) {
        String isbn;
        Matcher matcher;
        do {
            System.out.print("ISBN: ");
            isbn = sc.nextLine().trim();
            matcher = ISBN_PATTERN.matcher(isbn);
            if (!matcher.matches()) {
                System.err.println("Invalid ISBN format. Please match the pattern: ___-_-__-______-_.");
            } else {
                break;
            }
        } while (true);
        return isbn;
    }

    public static boolean readBooleanInput(Scanner sc) {
        boolean input;
        do {
            System.out.print("Add to favourites? (true/false)" + ": ");
            try {
                input = sc.nextBoolean();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter \"true\" or \"false.\"");
                sc.nextLine();
            }
        } while (true);
        return input;
    }

    private static String getGenres() {
        StringBuilder genres = new StringBuilder();
        for (Genre genre : Genre.values()) {
            genres.append(genre.name()).append(", ");
        }
        return genres.substring(0, genres.length() - 2);
    }
}
