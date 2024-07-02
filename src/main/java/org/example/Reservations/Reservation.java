package org.example.Reservations;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {
    int id;
    int clientId;
    LocalDateTime reservation_date;
    int numberOfPeople;
    String status;

    public Reservation(){};
    public Reservation(int id, int clientId, LocalDateTime reservation_date, int numberOfPeople) {
        this.id = id;
        this.clientId = clientId;
        this.reservation_date = reservation_date;
        this.numberOfPeople = numberOfPeople;
    }
    public Reservation(int id, int clientId, LocalDateTime reservation_date, int numberOfPeople, String status) {
        this.id = id;
        this.clientId = clientId;
        this.reservation_date = reservation_date;
        this.numberOfPeople = numberOfPeople;
        this.status = status;
    }

    public int getId() {return id;}
    public int getClientId() {return clientId;}
    public LocalDateTime getReservation_date() {return reservation_date;}
    public int getNumberOfPeople() {return numberOfPeople;}
    public String getStatus() {return status;}

    @Override
    public String toString() {
        return "Reservation ID: " + getId() + ". Client ID: " + getClientId() + ". Reservation date: "
                + getReservation_date() + ". People amount: " + getNumberOfPeople() + ". Reservation status: "  + getStatus() + ". ";
    }
}
