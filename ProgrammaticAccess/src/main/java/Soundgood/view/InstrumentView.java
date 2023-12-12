package Soundgood.view;

import Soundgood.controller.InstrumentController;
import Soundgood.model.Instrument;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class InstrumentView {

    private final Scanner scanner;
    private final InstrumentController instrumentController;

    public InstrumentView(InstrumentController instrumentController) {
        this.scanner = new Scanner(System.in);
        this.instrumentController = instrumentController;
    }

    public void showListInstrumentsView() throws SQLException {
        System.out.println("What type of instrument are you interested in (e.g., Guitar, Saxophone)?");
        String instrument = scanner.nextLine();
        System.out.println("Based on your preferences, we have found the following instrument that matches your request:");
        instrumentController.listInstruments(instrument);
    }

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