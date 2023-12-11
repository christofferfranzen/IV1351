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

    public void setInstrumentId(int instrumentId) {
        this.instrumentId = instrumentId;
    }

    public int getInstrumentTypeId() {
        return instrumentTypeId;
    }

    public void setInstrumentTypeId(int instrumentTypeId) {
        this.instrumentTypeId = instrumentTypeId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(int rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
}