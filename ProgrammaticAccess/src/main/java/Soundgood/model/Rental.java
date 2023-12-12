package Soundgood.model;

import java.util.Date;

public class Rental {
    private int studentId;
    private int instrumentId;
    private Date start_date;
    private Date end_date;

    public Rental(int studentId, int instrumentId, Date start_date, Date end_date) {
        this.studentId = studentId;
        this.instrumentId = instrumentId;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    // Getter och setter-metoder för alla fält

    public int getStudentId() {
        return studentId;
    }

    public int getInstrumentId() {
        return instrumentId;
    }

    public Date getStartDate() {
        return start_date;
    }

    public Date getEndDate() {
        return end_date;
    }

    public void setEndDate(Date endDate) {
        this.end_date = endDate;
    }
}