package Soundgood.dao;

import Soundgood.model.Rental;

import java.sql.SQLException;

public interface RentalDAO {
    void updateRental(Rental rental);

    Rental readRental(int studentId, int instrumentId);

    void createRental(Rental rental);

    int readNumberOfRentals(int studentId) throws SQLException, ClassNotFoundException;
}