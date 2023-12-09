package Soundgood.model;

public class Instrument {
    private int instrumentId;
    private int instrumentTypeId;
    private String serialNumber;
    private String brand;
    private int quantity;
    private int rentalPrice;

    // Constructors, getters, and setters

    public Instrument(int instrumentId, int instrumentTypeId, String serialNumber, String brand, int quantity, int rentalPrice) {
        this.instrumentId = instrumentId;
        this.instrumentTypeId = instrumentTypeId;
        this.serialNumber = serialNumber;
        this.brand = brand;
        this.quantity = quantity;
        this.rentalPrice = rentalPrice;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(int rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
}