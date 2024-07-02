package org.example.Reservations;

import org.example.Clients.Client;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReservationDB {
    final static String URL = "jdbc:mysql://localhost:3306/javadarbas";
    final static String USERNAME = "root";
    final static String PASSWORD = "l3g10n4s";
    public static Connection _connection;

    public void SQLconnect(String URL, String USERNAME, String PASSWORD) throws SQLException, SQLException {
        _connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void SQLconnect() throws SQLException {
        _connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void newReservation(int clientId, LocalDateTime reservation_date, int numberOfPeople) throws SQLException {
        SQLconnect();
        final String sql = "INSERT INTO reservations (client_id, reservation_date, number_of_people) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            Timestamp timestamp = Timestamp.valueOf(reservation_date);

            preparedStatement.setInt(1, clientId);
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setInt(3, numberOfPeople);
            preparedStatement.executeUpdate();
        }
    }

    public void confirmReservation(int reservationId) throws SQLException {
        SQLconnect();
        final String sql = "UPDATE reservations SET status = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            String status = "Confirmed";
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, reservationId);
            preparedStatement.executeUpdate();
        }
    }

    public void cancelReservation(int reservationId) throws SQLException {
        SQLconnect();
        final String sql = "UPDATE reservations SET status = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            String status = "Canceled";
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, reservationId);
            preparedStatement.executeUpdate();
        }
    }

    public List<Reservation> allReservations() throws SQLException {
        final String sql = "SELECT * FROM reservations";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Reservation> allReservations = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int clientId = resultSet.getInt("client_id");

                // Retrieve the date as a String from the ResultSet
                String reservationDateString = resultSet.getString("reservation_date");

                // Define the formatter to match the date string format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                // Convert the String to LocalDateTime
                LocalDateTime reservationDate = LocalDateTime.parse(reservationDateString, formatter);

                // Format the LocalDateTime to the desired string format
                String formattedDate = reservationDate.format(formatter);

                int numberOfPeople = resultSet.getInt("number_of_people");
                String status = resultSet.getString("status");
                if (status == null) {
                    status = "Pending";
                    Reservation r = new Reservation(id, clientId, reservationDate, numberOfPeople, status);
                    allReservations.add(r);
                } else {
                    Reservation r = new Reservation(id, clientId, reservationDate, numberOfPeople, status);
                    allReservations.add(r);
                }
            }
            return allReservations;
        }
    }

    public List<Reservation> allReservationsByClientId(int clientId2) throws SQLException {
        final String sql = "SELECT * FROM reservations WHERE client_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, clientId2);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Reservation> allReservationsByClientId = new ArrayList<>();


            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int clientId = resultSet.getInt("client_id");

                // Retrieve the date as a String from the ResultSet
                String reservationDateString = resultSet.getString("reservation_date");

                // Define the formatter to match the date string format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                // Convert the String to LocalDateTime
                LocalDateTime reservationDate = LocalDateTime.parse(reservationDateString, formatter);

                // Format the LocalDateTime to the desired string format
                String formattedDate = reservationDate.format(formatter);

                int numberOfPeople = resultSet.getInt("number_of_people");
                String status = resultSet.getString("status");
                if (status == null) {
                    status = "Pending";
                    Reservation r = new Reservation(id, clientId, reservationDate, numberOfPeople, status);
                    allReservationsByClientId.add(r);
                } else {
                    Reservation r = new Reservation(id, clientId, reservationDate, numberOfPeople, status);
                    allReservationsByClientId.add(r);
                }
            }
            return allReservationsByClientId;
        }
    }

    public List<Reservation> allReservationsByDate(String date) throws SQLException {
        final String sql = "SELECT * FROM reservations WHERE DATE_FORMAT(reservation_date, '%Y-%m-%d') LIKE ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, date);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Reservation> allReservationsByDate = new ArrayList<>();


            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int clientId = resultSet.getInt("client_id");

                // Retrieve the date as a String from the ResultSet
                String reservationDateString = resultSet.getString("reservation_date");

                // Define the formatter to match the date string format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                // Convert the String to LocalDateTime
                LocalDateTime reservationDate = LocalDateTime.parse(reservationDateString, formatter);

                // Format the LocalDateTime to the desired string format
                String formattedDate = reservationDate.format(formatter);

                int numberOfPeople = resultSet.getInt("number_of_people");
                String status = resultSet.getString("status");
                if (status == null) {
                    status = "Pending";
                    Reservation r = new Reservation(id, clientId, reservationDate, numberOfPeople, status);
                    allReservationsByDate.add(r);
                } else {
                    Reservation r = new Reservation(id, clientId, reservationDate, numberOfPeople, status);
                    allReservationsByDate.add(r);
                }
            }
            return allReservationsByDate;
        }
    }

    public List<Reservation> confirmedReservations() throws SQLException {
        SQLconnect();
        final String sql = "SELECT * FROM reservations WHERE status = 'Confirmed'";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            ResultSet resultSet = preparedStatement.executeQuery();
            List<Reservation> confirmedReservations = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int clientId = resultSet.getInt("client_id");

                // Retrieve the date as a String from the ResultSet
                String reservationDateString = resultSet.getString("reservation_date");

                // Define the formatter to match the date string format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                // Convert the String to LocalDateTime
                LocalDateTime reservationDate = LocalDateTime.parse(reservationDateString, formatter);

                // Format the LocalDateTime to the desired string format
                String formattedDate = reservationDate.format(formatter);

                int numberOfPeople = resultSet.getInt("number_of_people");
                String status = resultSet.getString("status");
                if (status == null) {
                    status = "Pending";
                    Reservation r = new Reservation(id, clientId, reservationDate, numberOfPeople, status);
                    confirmedReservations.add(r);
                } else {
                    Reservation r = new Reservation(id, clientId, reservationDate, numberOfPeople, status);
                    confirmedReservations.add(r);
                }
            }
            return confirmedReservations;
        }
    }
    public List<Reservation> canceledReservations() throws SQLException {
        SQLconnect();
        final String sql = "SELECT * FROM reservations WHERE status = 'Canceled'";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            ResultSet resultSet = preparedStatement.executeQuery();
            List<Reservation> canceledReservations = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int clientId = resultSet.getInt("client_id");

                // Retrieve the date as a String from the ResultSet
                String reservationDateString = resultSet.getString("reservation_date");

                // Define the formatter to match the date string format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                // Convert the String to LocalDateTime
                LocalDateTime reservationDate = LocalDateTime.parse(reservationDateString, formatter);

                // Format the LocalDateTime to the desired string format
                String formattedDate = reservationDate.format(formatter);

                int numberOfPeople = resultSet.getInt("number_of_people");
                String status = resultSet.getString("status");
                if (status == null) {
                    status = "Pending";
                    Reservation r = new Reservation(id, clientId, reservationDate, numberOfPeople, status);
                    canceledReservations.add(r);
                } else {
                    Reservation r = new Reservation(id, clientId, reservationDate, numberOfPeople, status);
                    canceledReservations.add(r);
                }
            }
            return canceledReservations;
        }
    }
    public void deleteReservationById(int reservationId) throws SQLException {
        SQLconnect();
        final String sql = "DELETE FROM reservations WHERE id = ?";
        try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,reservationId);
            preparedStatement.executeUpdate();
        }
    }

}







