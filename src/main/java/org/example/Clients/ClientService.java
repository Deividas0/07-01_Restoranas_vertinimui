package org.example.Clients;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    ClientDB cDB = new ClientDB();

    public void newClient(String name, String email, String phone) throws SQLException {
        cDB.newClient(name, email, phone);
    }
    public List<Client> allClients() throws SQLException {
        return cDB.allClients();
    }
}
