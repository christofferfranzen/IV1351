package Soundgood.dao;

import Soundgood.model.Rental;
import Soundgood.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class RentalDAOImpl implements RentalDAO {
    private static final String GET_RENTAL_BY_STUDENT_AND_INSTRUMENT_SQL = "SELECT * FROM rental WHERE student_id = ? AND instrument_id = ?";
    private static final String UPDATE_RENTAL_SQL = "UPDATE rental SET start_date = ?, end_date = ? WHERE student_id = ? AND instrument_id = ?";
    private static final String INSERT_RENTAL_SQL = "INSERT INTO rental (student_id, instrument_id, start_date, end_date) VALUES (?, ?, ?, ?)";
    private static final String COUNT_RENTALS_SQL = "SELECT COUNT(*) FROM rental WHERE student_id = ? AND end_date > CURRENT_DATE";
    @Override
    public Rental readRental(int studentId, int instrumentId) {
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

    @Override
    public void createRental(Rental rental) {
        try (Connection connection = DBUtil.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RENTAL_SQL)) {
                preparedStatement.setInt(1, rental.getStudentId());
                preparedStatement.setInt(2, rental.getInstrumentId());
                preparedStatement.setDate(3, new java.sql.Date(rental.getStartDate().getTime()));
                preparedStatement.setDate(4, new java.sql.Date(rental.getEndDate().getTime()));

                preparedStatement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int readNumberOfRentals(int studentId) throws SQLException, ClassNotFoundException {
        try (Connection connection = DBUtil.getConnection()){
            try {
                try (PreparedStatement preparedStatement = connection.prepareStatement(COUNT_RENTALS_SQL)) {
                    preparedStatement.setInt(1, studentId);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            return resultSet.getInt(1);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
