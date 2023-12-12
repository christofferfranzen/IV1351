package Soundgood.model;

public class Instrument {
    private int instrumentId;
    private String serialNumber;
    private String brand;
    private int rentalPrice;
    private int instrumentTypeId;

    // Constructors, getters, and setters

    public Instrument(int instrumentId, String serialNumber, String brand, int rentalPrice, int instrumentTypeId) {
        this.instrumentId = instrumentId;
        this.serialNumber = serialNumber;
        this.brand = brand;
        this.rentalPrice = rentalPrice;
        this.instrumentTypeId = instrumentTypeId;
    }

    // Getters and setters for each field

    public int getInstrumentId() {
        return instrumentId;
    }

    public int getInstrumentTypeId() {
        return instrumentTypeId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getBrand() {
        return brand;
    }

    public int getRentalPrice() {
        return rentalPrice;
    }
}