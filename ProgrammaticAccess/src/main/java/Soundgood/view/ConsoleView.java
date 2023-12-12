package Soundgood.view;

import Soundgood.controller.InstrumentController;
import Soundgood.controller.RentalController;
import Soundgood.model.Rental;

import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;
    private final InstrumentController instrumentController;
    private final InstrumentView instrumentView;
    private final RentalController rentalController;
    private final RentalView rentalView;
    private final TerminateRentalView terminateRentalView;

    public ConsoleView(InstrumentController instrumentController, RentalController rentalController) {
        this.scanner = new Scanner(System.in);
        this.instrumentController = instrumentController;
        this.instrumentView = new InstrumentView(instrumentController);
        this.rentalController = rentalController;
        this.rentalView = new RentalView(rentalController);
        this.terminateRentalView = new TerminateRentalView(rentalController);
    }

    public void start() throws SQLException {
        boolean exit = false;
        while (!exit) {
            System.out.println("Menu \n 1. List instruments of a certain kind \n 2. Rent instrument \n 3. Terminate rental \n 4. Exit");
            int choice = Integer.valueOf(scanner.nextLine());
            switch (choice) {
                case 1:
                    instrumentView.showListInstrumentsView();
                    break;
                case 2:
                    rentalView.showRentInstrumentView();
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