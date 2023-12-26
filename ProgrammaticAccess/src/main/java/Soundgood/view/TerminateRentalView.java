package Soundgood.view;

import Soundgood.controller.RentalController;

import java.util.Scanner;

public class TerminateRentalView {
    private final Scanner scanner;
    private final RentalController rentalController;

    public TerminateRentalView(RentalController rentalController) {
        this.scanner = new Scanner(System.in);
        this.rentalController = rentalController;
    }

    public void showTerminateRentalView() {
        System.out.println("Do you want to terminate your rental? (yes/no)");
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("yes")) {
            System.out.println("Please enter your student ID:");
            int studentId = Integer.parseInt(scanner.nextLine());

            System.out.println("Please enter the instrument ID:");
            int instrumentId = Integer.parseInt(scanner.nextLine());

            int statusCode = rentalController.terminateRental(studentId, instrumentId);

            handleTerminateRentalResult(statusCode);
        } else if (userInput.equalsIgnoreCase("no")) {
            System.out.println("Continuing rental...");
        } else {
            System.out.println("Invalid input. Please answer with 'yes' or 'no'.");
        }
    }

    private void handleTerminateRentalResult(int statusCode) {
        switch (statusCode) {
            case 0:
                System.out.println("Rental terminated successfully.");
                break;
            case 1:
                System.out.println("No rental found for the provided IDs.");
                break;
            case 2:
                System.out.println("Error terminating rental.");
                break;
            default:
                System.out.println("Unexpected result code: " + statusCode);
        }
    }
}