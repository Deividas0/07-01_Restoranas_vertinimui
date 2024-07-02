package org.example.Clients;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDB {
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


    public void newClient(String name, String email, String phone) throws SQLException {
        SQLconnect();
        final String sql = "INSERT INTO clients (name, email, phone) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.executeUpdate();
        }
    }

    public List<Client> allClients() throws SQLException {
        SQLconnect();
        final String sql = "SELECT * FROM clients";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Client> allClients = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                Client client = new Client(id, name, email, phone);
                allClients.add(client);
            }
            return allClients;
        }
    }
}
