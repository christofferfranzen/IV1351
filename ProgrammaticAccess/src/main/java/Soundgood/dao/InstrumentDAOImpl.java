package Soundgood.dao;

import Soundgood.model.Instrument;
import Soundgood.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstrumentDAOImpl implements InstrumentDAO {
    private static final String GET_AVAILABLE_INSTRUMENTS_SQL =
                    "SELECT i.instrument_id, i.serial_number, ir.brand, ir.price, ir.instrument_type_id " +
                    "FROM instrument i " +
                    "JOIN instrument_information_relation iir ON i.instrument_id = iir.instrument_id " +
                    "JOIN instrument_rent ir ON iir.instrument_rent_id = ir.instrument_rent_id " +
                    "JOIN rental r ON i.instrument_id = r.instrument_id " +
                    "WHERE CURRENT_DATE NOT BETWEEN r.start_date AND r.end_date";

    private static final String GET_ALL_INSTRUMENT_TYPE_ID_SQL = "SELECT * FROM instrument_type";

    @Override
    public List<Instrument> getAllInstruments(String instrumentType) {
        List<Instrument> instruments = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection()) {
            int instrumentTypeId = getInstrumentTypeId(connection, instrumentType);

            if (instrumentTypeId != -1) {
                instruments = getInstrumentsByTypeId(connection, instrumentTypeId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return instruments;
    }

    private int getInstrumentTypeId(Connection connection, String instrumentType) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultInstrumentTypeSet = statement.executeQuery(GET_ALL_INSTRUMENT_TYPE_ID_SQL)) {

            while (resultInstrumentTypeSet.next()) {
                if (resultInstrumentTypeSet.getString("value").equals(instrumentType)) {
                    return resultInstrumentTypeSet.getInt("instrument_type_id");
                }
            }
        }

        return -1; // Not found
    }

    private List<Instrument> getInstrumentsByTypeId(Connection connection, int instrumentTypeId) {
        List<Instrument> instruments = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_AVAILABLE_INSTRUMENTS_SQL)) {

            while (resultSet.next()) {
                if (resultSet.getInt("instrument_type_id") == instrumentTypeId) {
                    Instrument instrumentData = mapResultSetToInstrument(resultSet);
                    instruments.add(instrumentData);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return instruments;
    }

    private Instrument mapResultSetToInstrument(ResultSet resultSet) throws SQLException {
        return new Instrument(
                resultSet.getInt("instrument_id"),
                resultSet.getString("serial_number"),
                resultSet.getString("brand"),
                resultSet.getInt("price"),
                resultSet.getInt("instrument_type_id")
        );
    }
}