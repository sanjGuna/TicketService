package ticketservice.dao;

import java.util.Date;
import java.util.List;

import ticketservice.entity.Reservation;
import ticketservice.mockdb.TicketServiceDB;

/**
 * This will help to access Reservation data
 */
public class ReservationDao {

    public Integer saveReservation(List<Reservation> reservations) {
        return TicketServiceDB.saveReservationBatch(reservations);
    }

    public Reservation findReservation(Long reservationId) {
        return TicketServiceDB.getReservation(reservationId);
    }

    public List<Reservation> findReservationsByDate(Date date) {
        return TicketServiceDB.getReservationByDate(date);
    }

    public void expireReservations() {
        TicketServiceDB.expireReservations();
    }

    public boolean confirmReservation(int groupId) {
        return TicketServiceDB.confirmReservation(groupId);
    }

    public boolean isSeatAvailable(List<Long> seatIds, Date date) {
        return TicketServiceDB.isSeatAvailable(seatIds, date);
    }
}
