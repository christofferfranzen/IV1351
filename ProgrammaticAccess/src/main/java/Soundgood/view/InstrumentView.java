package Soundgood.view;

import Soundgood.model.Instrument;

import java.util.List;

public class InstrumentView {
    public static void displayInstruments(List<Instrument> instruments) {
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
        System.out.println("Serial Number: " + instrument.getSerialNumber());
        System.out.println("Brand: " + instrument.getBrand());
        System.out.println("Rental Price: " + instrument.getRentalPrice());
        System.out.println("Instrument Type: " + instrument.getInstrumentTypeId());
        System.out.println("--------------------");
    }
}