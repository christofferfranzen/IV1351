package Soundgood.view;

import Soundgood.controller.InstrumentController;
import Soundgood.controller.RentalController;

import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;
    private final InstrumentController instrumentController;
    private final RentalController rentalController;
    private final TerminateRentalView terminateRentalView;

    public ConsoleView(InstrumentController instrumentController, RentalController rentalController) {
        this.scanner = new Scanner(System.in);
        this.instrumentController = instrumentController;
        this.rentalController = rentalController;
        this.terminateRentalView = new TerminateRentalView(rentalController);
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
                    // TODO: Task 2 Ludwig
                    // rentalController.rentInstrument();
                    break;
                case 3:
                    terminateRentalView.showTerminateRentalView();
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