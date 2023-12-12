package Soundgood.view;

import Soundgood.model.Rental;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RentalView {

    private final Scanner scanner;

    public RentalView() {
        this.scanner = new Scanner(System.in);
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

            // Print the entered data
            System.out.println("Rental created: " + rental);

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