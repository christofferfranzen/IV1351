package Soundgood.dao;

import Soundgood.model.Instrument;
import Soundgood.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstrumentDAOImpl implements InstrumentDAO {
    private static final String GET_ALL_INSTRUMENTS_SQL = "SELECT * FROM instrument";
    @Override
    public List<Instrument> getAllInstruments() {
        List<Instrument> instruments = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_INSTRUMENTS_SQL)) {

            while (resultSet.next()) {
                Instrument instrument = mapResultSetToInstrument(resultSet);
                instruments.add(instrument);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return instruments;
    }

    // Helper method to map a ResultSet to an Instrument object
    private Instrument mapResultSetToInstrument(ResultSet resultSet) throws SQLException {
        return new Instrument(
                resultSet.getInt("instrument_id"),
                resultSet.getInt("instrument_type_id"),
                resultSet.getString("serial_number"),
                resultSet.getString("brand"),
                resultSet.getInt("quantity"),
                resultSet.getInt("rental_price")
        );
    }
}