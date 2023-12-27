package Soundgood.integration;

import Soundgood.model.Rental;

import java.util.Date;

public class RentalService {
    private final RentalDAO rentalDAO;

    public RentalService(RentalDAO rentalDAO) {
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

    public int rentInstrument(int studentId, int instrumentId, Date startDate, Date endDate) {
        try {
            // Check the number of existing rentals for the student
            int existingRentals = rentalDAO.readNumberOfRentals(studentId);

            if (existingRentals < 2) {
                // If the student has fewer than two rentals, proceed with the rental
                Rental rental = new Rental(studentId, instrumentId, startDate, endDate);
                rentalDAO.createRental(rental);

                return 0; // Success code
            } else {
                // If the student already has two rentals, reject the rental
                return 1; // Max rentals reached
            }
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return 2; // Error during rental
        }
    }
}