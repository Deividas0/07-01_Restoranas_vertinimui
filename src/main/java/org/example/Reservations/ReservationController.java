package org.example.Reservations;

import org.example.Clients.Client;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ReservationController {
    ReservationService rs = new ReservationService();

    @PostMapping("/reservations") // New Reservation.
    public Reservation newReservation(@RequestBody Reservation r) throws SQLException {
        rs.newReservation(r.getClientId(), r.getReservation_date(), r.getNumberOfPeople());
        return null;
    }
    @PatchMapping("/reservations/confirm/{reservationId}") // Confirm reservation.
    public void confirmReservation(@PathVariable int reservationId) throws SQLException {
        rs.confirmReservation(reservationId);
    }
    @PatchMapping("/reservations/cancel/{reservationId}") // Cancel reservation.
    public void cancelReservation(@PathVariable int reservationId) throws SQLException {
        rs.cancelReservation(reservationId);
    }
    @GetMapping("/reservations") // All reservations.
    public List<Reservation> allReservations(@RequestParam(value = "date", required = false) String date) throws SQLException {
        if(date != null){
            return rs.allReservationsByDate(date);

        }else{
            return rs.allReservations();
        }
    }
    @GetMapping("/reservations/client/{clientId2}") // Get reservation by client ID.
    public List<Reservation> allReservationsByClientId(@PathVariable int clientId2) throws SQLException {
        return rs.allReservationsByClientId(clientId2);
    }
    @GetMapping("/reservations/confirmed")
    public List<Reservation> confirmedReservations() throws SQLException {
        return rs.confirmedReservations();
    }
    @GetMapping("/reservations/canceled")
    public List<Reservation> canceledReservations() throws SQLException {
        return rs.canceledReservations();
    }
    @DeleteMapping("/reservations/{reservationId}")
    public void deleteReservationById(@PathVariable int reservationId) throws SQLException {
        rs.deleteReservationById(reservationId);
    }
}

