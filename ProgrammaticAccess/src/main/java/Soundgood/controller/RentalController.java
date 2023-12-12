package Soundgood.controller;

import Soundgood.dao.RentalDAO;
import Soundgood.model.Rental;

import java.util.Date;


public class RentalController {
    private final RentalDAO rentalDAO;

    public RentalController(RentalDAO rentalDAO) {
        this.rentalDAO = rentalDAO;
    }

    public static void rentInstrument() {
        // Implement code to rent an instrument
        System.out.println("Renting instrument...");
    }

    public int terminateRental(int studentId, int instrumentId) {
        try {
            Rental rentalToTerminate = rentalDAO.getRental(studentId, instrumentId);

            if (rentalToTerminate != null) {
                rentalToTerminate.setEndDate(new Date());
                rentalDAO.updateRental(rentalToTerminate);
                return 0; // Success code
            } else {
                return 1; // No rental found
            }
        } catch (Exception e) {
            // Handle or log the exception
            return 2; // Error during termination
        }
    }
}
