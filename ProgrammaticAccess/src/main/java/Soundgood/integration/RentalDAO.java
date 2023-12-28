package Soundgood.integration;

import Soundgood.model.Rental;

import java.sql.SQLException;

public interface RentalDAO {
    void updateRental(Rental rental) throws SQLException;

    Rental readRental(int studentId, int instrumentId) throws SQLException;

    void createRental(Rental rental) throws SQLException;

    int readNumberOfRentals(int studentId) throws SQLException, ClassNotFoundException;
}