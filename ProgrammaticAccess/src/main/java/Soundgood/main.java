package Soundgood;

import Soundgood.controller.InstrumentController;
import Soundgood.controller.RentalController;
import Soundgood.dao.InstrumentDAOImpl;
import Soundgood.dao.RentalDAOImpl;
import Soundgood.view.ConsoleView;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        InstrumentController instrumentController = new InstrumentController(new InstrumentDAOImpl());
        RentalController rentalController = new RentalController(new RentalDAOImpl());

        ConsoleView consoleView = new ConsoleView(instrumentController, rentalController);
        consoleView.start();
    }
}
