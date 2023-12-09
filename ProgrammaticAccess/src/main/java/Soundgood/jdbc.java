package Soundgood;

import java.sql.*;
import java.util.Scanner;

public class jdbc {
    private static final String TABLE_NAME = "person";
    private PreparedStatement createPersonStmt;
    private PreparedStatement findAllPersonsStmt;
    private PreparedStatement deletePersonStmt;

    private void accessDB() {
        try (Connection connection = createConnection()) {
            createTable(connection);
            prepareStatements(connection);
            createPersonStmt.setString(1, "stina");
            createPersonStmt.setString(2, "0123456789");
            createPersonStmt.setInt(3, 43);
            createPersonStmt.executeUpdate();
            createPersonStmt.setString(1, "olle");
            createPersonStmt.setString(2, "9876543210");
            createPersonStmt.setInt(3, 12);
            createPersonStmt.executeUpdate();
            listAllRows();
            deletePersonStmt.setString(1, "stina");
            deletePersonStmt.executeUpdate();
            listAllRows();
        } catch (SQLException | ClassNotFoundException exc) {
            exc.printStackTrace();
        }
    }

    private Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/simplejdbc",
                "postgres", "postgres");
        // Class.forName("com.mysql.cj.jdbc.Driver");
        // return DriverManager.getConnection(
        // "jdbc:mysql://localhost:3306/simplejdbc?serverTimezone=UTC",
        // "root", "javajava");
    }

    private void createTable(Connection connection) {
        try (Statement stmt = connection.createStatement()) {
            if (!tableExists(connection)) {
                stmt.executeUpdate(
                        "create table " + TABLE_NAME + " (name varchar(32) primary key, phone varchar(12), age int)");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    private boolean tableExists(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tableMetaData = metaData.getTables(null, null, null, null);
        while (tableMetaData.next()) {
            String tableName = tableMetaData.getString(3);
            if (tableName.equalsIgnoreCase(TABLE_NAME)) {
                return true;
            }
        }
        return false;
    }

    private void listAllRows() {
        try (ResultSet persons = findAllPersonsStmt.executeQuery()) {
            while (persons.next()) {
                System.out.println(
                        "name: " + persons.getString(1) + ", phone: " + persons.getString(2) + ", age: " + persons.getInt(3));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    private void prepareStatements(Connection connection) throws SQLException {
        createPersonStmt = connection.prepareStatement("INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?)");
        findAllPersonsStmt = connection.prepareStatement("SELECT * from " + TABLE_NAME);
        deletePersonStmt = connection.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE name = ?");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean avsluta = false;

        while (avsluta == false){
            System.out.println("Menu \n 1. List instruments \n 2. Rent instrument \n 3. Terminate rental \n 4. Avsluta");
            int alt = Integer.valueOf(scanner.nextLine());

            switch (alt){
                case 1:
                    System.out.println("112333");
                    new jdbc().accessDB();
                    // Skriv kod som gör uppgift 1 chrfra
                    break;
                case 2:
                    System.out.println("2");
                    // Skriv kod som gör uppgift 1 Ludwig
                    break;
                case 3:
                    System.out.print("3");
                    // Skriv kod som gör uppgift 1 Harry

                    System.out.println("Hammarby är bäst");
                    break;
                case 4:
                    System.out.println("4");
                    avsluta = true;
                    break;
            }
        }
        System.out.println("Hej då!");
    }
}