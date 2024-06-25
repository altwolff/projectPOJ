package enums;
import exceptions.InvalidSearchFieldException;

public enum SearchField {
    TITLE,
    AUTHOR,
    PUBLISHER,
    YEAR,
    PAGES,
    GENRE,
    ISBN;

    public static SearchField fromString(String field) throws InvalidSearchFieldException {
        return switch (field.toLowerCase()) {
            case "title" -> TITLE;
            case "author" -> AUTHOR;
            case "publisher" -> PUBLISHER;
            case "year" -> YEAR;
            case "pages" -> PAGES;
            case "genre" -> GENRE;
            case "isbn" -> ISBN;
            default -> throw new InvalidSearchFieldException("Invalid search field: " + field);
        };
    }
}
