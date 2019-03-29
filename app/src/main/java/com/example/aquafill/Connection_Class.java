package com.example.aquafill;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection_Class {

    public static void main(String[] args) {
        String connectionUrl =
                "jdbc:jtds:sqlserver://aquafillapplication.database.windows.net:1433;"
                        + "DatabaseName=AquaFill_Locations;"
                        + "user=aquafilladmin@aquafillapplication;"
                        + "password=Help4Heroes;"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";

        getData(connectionUrl);
        sendData(connectionUrl);

    }

    private static void getData(String connectionUrl) {
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT TOP 4 location_id, location_type, location_name from dbo.location_information";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void sendData(String connectionUrl) {
        String insertSql = "INSERT INTO dbo.location_information (location_id, location_type, location_name, " +
                "location_latitude, location_longitude, location_upvotes, location_downvotes) VALUES "
                + "('50', '2', 'Derby Rd', 50, 120, '0', '0');";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsInsertProduct.execute();
            // Retrieve the generated key from the insert.
            resultSet = prepsInsertProduct.getGeneratedKeys();

            // Print the ID of the inserted row.
            while (resultSet.next()) {
                System.out.println("Generated: " + resultSet.getString(1));
            }
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateData(String connectionURL){

        String updateSql = "UPDATE IMTo";

        ResultSet resultSet = null;
    }
}

