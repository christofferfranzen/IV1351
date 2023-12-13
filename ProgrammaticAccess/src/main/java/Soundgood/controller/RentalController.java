package Soundgood.controller;

import Soundgood.dao.RentalDAO;
import Soundgood.model.Rental;

import java.util.Date;

public class RentalController {
    private final RentalDAO rentalDAO;

    public RentalController(RentalDAO rentalDAO) {
        this.rentalDAO = rentalDAO;
    }

    public int terminateRental(int studentId, int instrumentId) {
        try {
            Rental rentalToTerminate = rentalDAO.readRental(studentId, instrumentId);

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

    public void rentInstrument(int studentId, int instrumentId, java.util.Date startDate, java.util.Date endDate) {
        try {
            // Check the number of existing rentals for the student
            int existingRentals = rentalDAO.readNumberOfRentals(studentId);

            if (existingRentals < 2) {
                // If the student has fewer than two rentals, proceed with the rental
                Rental rental = new Rental(studentId, instrumentId, startDate, endDate);

                // Log the rented instrument
                System.out.println("Rented instrument: " + rental);

                rentalDAO.createRental(rental);
            } else {
                // If the student already has two rentals, reject the rental
                System.out.println("Student already has the maximum allowed number of rentals (2). Cannot rent more instruments.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
