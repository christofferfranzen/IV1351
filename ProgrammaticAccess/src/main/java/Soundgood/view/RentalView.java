package Soundgood.view;

import Soundgood.controller.RentalController;
import Soundgood.model.Rental;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RentalView {

    private final Scanner scanner;
    private final RentalController rentalController;

    public RentalView(RentalController rentalController) {
        this.scanner = new Scanner(System.in);
        this.rentalController = rentalController;
    }

    public void showRentInstrumentView() {
        Rental rental = createRental();
        if (rental != null) {
            int resultCode = rentalController.rentInstrument(
                    rental.getStudentId(), rental.getInstrumentId(),
                    rental.getStartDate(), rental.getEndDate());

            handleRentInstrumentResult(resultCode);
        }
    }

    private void handleRentInstrumentResult(int resultCode) {
        switch (resultCode) {
            case 0:
                System.out.println("Instrument rented successfully.");
                break;
            case 1:
                System.out.println("Student already has the maximum allowed number of rentals (2). Cannot rent more instruments.");
                break;
            case 2:
                System.out.println("Error renting instrument. Please try again.");
                break;
            default:
                System.out.println("Unexpected result code: " + resultCode);
        }
    }

    public Rental createRental() {
        try {
            System.out.println("Enter studentID: ");
            int studentID = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter InstrumentID: ");
            int instrumentID = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter start date (YYYY-MM-DD): ");
            String startDateString = scanner.nextLine();
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateString);

            System.out.println("Enter end date (YYYY-MM-DD): ");
            String endDateString = scanner.nextLine();
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateString);

            Rental rental = new Rental(studentID, instrumentID, startDate, endDate);

            // No printout in the controller, so comment out the following line
            // System.out.println("Rental created: " + rental);

            return rental;
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return null;
        }
    }
}
