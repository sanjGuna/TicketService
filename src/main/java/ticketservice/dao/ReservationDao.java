package ticketservice.dao;

import java.util.Date;
import java.util.List;

import ticketservice.entity.Reservation;
import ticketservice.mockdb.TicketServiceDB;

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

    public void confirmReservation(int groupId) {
        TicketServiceDB.confirmReservation(groupId);

    }

    public boolean IsSeatAvailable(List<Long> seatIds, Date date) {
        return TicketServiceDB.IsSeatAvailable(seatIds, date);
    }
}
