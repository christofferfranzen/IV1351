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

    public void terminateRental(int studentId, int instrumentId) {
        // Hämta uthyrningen baserat på studentId och instrumentId från databasen
        Rental rentalToTerminate = rentalDAO.getRental(studentId, instrumentId);

        if (rentalToTerminate != null) {
            // Uppdatera end_date till dagens datum
            rentalToTerminate.setEndDate(new Date());

            // Uppdatera uthyrningen i databasen
            rentalDAO.updateRental(rentalToTerminate);

            System.out.println("Rental terminated successfully.");
        } else {
            System.out.println("No rental found for the provided IDs.");
        }
    }
}