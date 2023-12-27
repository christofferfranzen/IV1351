package Soundgood.integration;

import Soundgood.model.Instrument;

import java.sql.SQLException;
import java.util.List;

public interface InstrumentDAO {
    List<Instrument> readAllInstruments(String instrumentType) throws SQLException;
}
