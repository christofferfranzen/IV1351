package Soundgood.dao;

import Soundgood.model.Rental;

import java.util.List;

public interface RentalDAO {
    List<Rental> getAllRentals();

    void updateRental(Rental rental);

    Rental getRental(int studentId, int instrumentId);
}