package Soundgood.dao;

import Soundgood.model.Rental;

import java.sql.SQLException;
import java.util.List;

public interface RentalDAO {
    void updateRental(Rental rental);

    Rental getRental(int studentId, int instrumentId);

    void rentInstrument(Rental rental);

    int numberOfRentals(int studentId) throws SQLException, ClassNotFoundException;
}