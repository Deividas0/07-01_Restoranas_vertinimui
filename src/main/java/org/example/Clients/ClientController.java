package org.example.Clients;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ClientController {
    ClientService cs = new ClientService();

    @PostMapping("/clients") // New Client.
    public Client newClient(@RequestBody Client c) throws SQLException {
        cs.newClient(c.getName(), c.getEmail(), c.getPhone());
        return null;
    }
    @GetMapping("/clients") // All Clients.
    public List<Client> allClients() throws SQLException {
        return cs.allClients();
    }


}
