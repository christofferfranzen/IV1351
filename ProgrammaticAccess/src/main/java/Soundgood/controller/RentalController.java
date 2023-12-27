package Soundgood.controller;

import Soundgood.integration.RentalDAO;
import Soundgood.integration.RentalService;

import java.util.Date;

public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalDAO rentalDAO) {
        this.rentalService = new RentalService(rentalDAO);
    }

    public int terminateRental(int studentId, int instrumentId) {
        return rentalService.terminateRental(studentId, instrumentId);
    }

    public int rentInstrument(int studentId, int instrumentId, Date startDate, Date endDate) {
        return rentalService.rentInstrument(studentId, instrumentId, startDate, endDate);
    }
}