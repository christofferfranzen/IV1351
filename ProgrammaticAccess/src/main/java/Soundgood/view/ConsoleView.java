package Soundgood.view;

import Soundgood.controller.InstrumentController;
import Soundgood.controller.RentalController;

import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;
    private final InstrumentController instrumentController;
    private final RentalController rentalController;

    public ConsoleView(InstrumentController instrumentController, RentalController rentalController) {
        this.scanner = new Scanner(System.in);
        this.instrumentController = instrumentController;
        this.rentalController = rentalController;
    }

    public void start() throws SQLException {
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu \n 1. List instruments of a certain kind \n 2. Rent instrument \n 3. Terminate rental \n 4. Exit");
            int choice = Integer.valueOf(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("What type of instrument are you interested in (e.g., Guitar, Saxophone)?");
                    String instrument = scanner.nextLine();
                    System.out.println("Based on your preferences, we have found the following instrument that matches your request:");
                    instrumentController.listInstruments(instrument);
                    break;
                case 2:
                    //TODO: Task 2 Ludwig
                    //rentalController.rentInstrument();
                    break;
                case 3:
                    //TODO: Task 3 Harry
                    System.out.println("Do you want to terminate your rental? (yes/no)");
                    String userInput = scanner.nextLine(); // Omvandla till gemener för enklare jämförelse

                    if (userInput.equalsIgnoreCase("yes")) {
                        // Åtgärder om användaren svarar "yes"
                        System.out.println("Please enter your student ID:");
                        int studentId = Integer.parseInt(scanner.nextLine());

                        System.out.println("Please enter the instrument ID:");
                        int instrumentId = Integer.parseInt(scanner.nextLine());

                        rentalController.terminateRental(studentId, instrumentId);


                    } else if (userInput.equalsIgnoreCase("no")) {
                        // Åtgärder om användaren svarar "no"
                        System.out.println("Continuing rental...");
                    } else {
                        // Felhantering om användaren svarar något annat än "yes" eller "no"
                        System.out.println("Invalid input. Please answer with 'yes' or 'no'.");
                    }
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Goodbye!");
    }
}