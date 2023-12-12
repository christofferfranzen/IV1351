package Soundgood.dao;

import Soundgood.model.Rental;
import Soundgood.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalDAOImpl implements RentalDAO {
    private static final String GET_RENTAL_BY_STUDENT_AND_INSTRUMENT_SQL = "SELECT * FROM rental WHERE student_id = ? AND instrument_id = ?";
    private static final String UPDATE_RENTAL_SQL = "UPDATE rental SET start_date = ?, end_date = ? WHERE student_id = ? AND instrument_id = ?";
    private static final String GET_ALL_RENTALS_SQL = "SELECT * FROM rental";

    @Override
    public Rental getRental(int studentId, int instrumentId) {
        Rental rental = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_RENTAL_BY_STUDENT_AND_INSTRUMENT_SQL)) {

            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, instrumentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                rental = new Rental(studentId, instrumentId, startDate, endDate);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return rental;
    }

    @Override
    public List<Rental> getAllRentals() {
        List<Rental> rentals = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_RENTALS_SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                int instrumentId = resultSet.getInt("instrument_id");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");

                Rental rental = new Rental(studentId, instrumentId, startDate, endDate);
                rentals.add(rental);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return rentals;
    }

    @Override
    public void updateRental(Rental rental) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RENTAL_SQL)) {

            preparedStatement.setDate(1, new java.sql.Date(rental.getStartDate().getTime()));
            preparedStatement.setDate(2, new java.sql.Date(rental.getEndDate().getTime()));
            preparedStatement.setInt(3, rental.getStudentId());
            preparedStatement.setInt(4, rental.getInstrumentId());

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}