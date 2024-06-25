package services;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ChoiceTable {
    public static void displayChoiceTable() {
        System.out.println("""
                        
                ++++++++++++++++++++++++++++++++
                |  Virtual Library ver. 1.0.0  |
                |                              |
                |  Please select:              |
                |  1. Add a book               |
                |  2. Delete a book            |
                |  3. Search library           |
                |  4. View all                 |
                |  5. View favourites          |
                |  6. Quit                     |
                |                              |
                ++++++++++++++++++++++++++++++++
                """);
    }

    public static int getUserChoice(Scanner sc) {
        int choice = 0;
        boolean validInput = false;
        displayChoiceTable();

        while (!validInput) {
            try {
                choice = sc.nextInt();
                if (choice >= 1 && choice <= 6) {
                    validInput = true;
                } else {
                    System.err.println("Invalid choice. Please retry.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number.");
                sc.next();
            }
        }
        return choice;
    }
}
