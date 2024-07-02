package org.example.Reservations;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationService {

    ReservationDB rDB = new ReservationDB();

    public void newReservation(int clientId, LocalDateTime reservation_date, int numberOfPeople) throws SQLException {
        rDB.newReservation(clientId,reservation_date,numberOfPeople);
    }
    public void confirmReservation(int reservationId) throws SQLException {
        rDB.confirmReservation(reservationId);
    }
    public void cancelReservation(int reservationId) throws SQLException {
        rDB.cancelReservation(reservationId);
    }
    public List<Reservation> allReservations() throws SQLException {
       return rDB.allReservations();
    }
    public List<Reservation> allReservationsByClientId(int clientId2) throws SQLException {
        return rDB.allReservationsByClientId(clientId2);
    }
    public List<Reservation> allReservationsByDate(String date) throws SQLException {
        return rDB.allReservationsByDate(date);
    }
    public List<Reservation> confirmedReservations() throws SQLException {
        return rDB.confirmedReservations();
    }
    public List<Reservation> canceledReservations() throws SQLException {
        return rDB.canceledReservations();
    }
    public void deleteReservationById(int reservationId) throws SQLException {
        rDB.deleteReservationById(reservationId);
    }
}
