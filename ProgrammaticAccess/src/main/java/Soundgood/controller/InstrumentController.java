package Soundgood.controller;

import Soundgood.dao.InstrumentDAO;
import Soundgood.model.Instrument;
import Soundgood.view.InstrumentView;

import java.sql.SQLException;
import java.util.List;

public class InstrumentController {
    private final InstrumentDAO instrumentDAO;

    public InstrumentController(InstrumentDAO instrumentDAO) {
        this.instrumentDAO = instrumentDAO;
    }

    public void listInstruments(String instrument) throws SQLException {
        List<Instrument> instruments = instrumentDAO.readAllInstruments(instrument);
        InstrumentView.displayInstruments(instruments);
    }
}