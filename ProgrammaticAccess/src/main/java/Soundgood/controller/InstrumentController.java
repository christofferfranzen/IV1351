package Soundgood.controller;

import Soundgood.dao.InstrumentDAO;
import Soundgood.model.Instrument;

import java.util.List;

public class InstrumentController {
    private InstrumentDAO instrumentDAO;

    public InstrumentController(InstrumentDAO instrumentDAO) {
        this.instrumentDAO = instrumentDAO;
    }

    public void listInstruments() {
        List<Instrument> instruments = instrumentDAO.getAllInstruments();

        if (instruments.isEmpty()) {
            System.out.println("No instruments found.");
        } else {
            System.out.println("Listing instruments:");
            for (Instrument instrument : instruments) {
                displayInstrumentInfo(instrument);
            }
        }
    }

    private static void displayInstrumentInfo(Instrument instrument) {
        System.out.println("Instrument ID: " + instrument.getInstrumentId());
        System.out.println("Instrument Type ID: " + instrument.getInstrumentTypeId());
        System.out.println("Serial Number: " + instrument.getSerialNumber());
        System.out.println("Brand: " + instrument.getBrand());
        System.out.println("Quantity: " + instrument.getQuantity());
        System.out.println("Rental Price: " + instrument.getRentalPrice());
        System.out.println("--------------------");
    }
}
