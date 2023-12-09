package Soundgood.view;

import Soundgood.controller.InstrumentController;
import Soundgood.controller.RentalController;

import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu \n 1. List instruments \n 2. Rent instrument \n 3. Terminate rental \n 4. Exit");
            int choice = Integer.valueOf(scanner.nextLine());

            switch (choice) {
                case 1:
                    InstrumentController.listInstruments();
                    break;
                case 2:
                    RentalController.rentInstrument();
                    break;
                case 3:
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