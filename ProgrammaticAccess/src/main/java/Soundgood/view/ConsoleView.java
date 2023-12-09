package Soundgood.view;

import Soundgood.controller.InstrumentController;
import Soundgood.controller.RentalController;

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

    public void start() {
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu \n 1. List instruments \n 2. Rent instrument \n 3. Terminate rental \n 4. Exit");
            int choice = Integer.valueOf(scanner.nextLine());
            switch (choice) {
                case 1:
                    instrumentController.listInstruments();
                    break;
                case 2:
                    //TODO: Task 2 Ludwig
                    RentalController.rentInstrument();
                    break;
                case 3:
                    //TODO: Task 3 Harry
                    RentalController.terminateRental();
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