package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BallonDorServiceImpl ballonDorService = new BallonDorServiceImpl();

        // Connect to the database
        ballonDorService.connect();

        Scanner scanner = new Scanner(System.in);
        boolean continueSearching = true;

        while (continueSearching) {
            // Get user input for year
            System.out.println("*******************************************");
            System.out.println("*                                         *");
            System.out.println("*   Welcome to the Ballon d'Or Management System   *");
            System.out.println("*                                         *");
            System.out.println("*******************************************");
            System.out.println();
            System.out.println("   1. ➤ Enter year or type exit to quit");
            System.out.println();
            System.out.println("*******************************************");
            System.out.println("*   Please select an option from 1    *");
            System.out.println("*******************************************");
            String input = scanner.nextLine();

            // Check if the user wants to exit
            if (input.equalsIgnoreCase("exit")) {
                continueSearching = false;
                System.out.println("Exiting the program.");
                break;
            }

            try {
                int year = Integer.parseInt(input); // Parse the input to an integer

                // Retrieve Ballon d'Or winners for the specified year
                ballonDorService.getBallonDorWinners(year);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid year.");
            }
        }

        // Close the database connection
        ballonDorService.closeConnection();

        // Close scanner
        scanner.close();
    }
}