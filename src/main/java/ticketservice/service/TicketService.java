package ticketservice.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import ticketservice.dao.ReservationDao;
import ticketservice.dao.SeatDao;
import ticketservice.entity.Reservation;
import ticketservice.entity.Seat;
import ticketservice.exception.InvalidDateException;

public class TicketService {


    public Map<Long, Seat> findAvailableSeats(Date date) throws InvalidDateException {
        validateDate(date);
        Map<Long, Seat> allSeats = new SeatDao().findAllSeats();
        new ReservationDao().expireReservations();
        List<Reservation> temporaryReservations = new ReservationDao().findReservationsByDate(date);

        for (Reservation temporaryReservation : temporaryReservations) {
            allSeats.remove(temporaryReservation.getSeatId());
        }
        return allSeats;
    }

    private void validateDate(Date date) throws InvalidDateException {
        if(date.before(new Date())){
            throw new InvalidDateException("Invalid Date. You can only see availability for Future dates");
        }
    }

    public Integer holdSeat(Date date, List<Long> seatIds, String userId) throws InvalidDateException {
        validateDate(date);
        List<Reservation> reservations = buildReservations(date, seatIds, userId);
        return new ReservationDao().saveReservation(reservations);
    }

    private List<Reservation> buildReservations(Date date, List<Long> seatIds, String userId) {
        List<Reservation> reservations = new ArrayList<>();
        for (Long seatId : seatIds) {
            Reservation reservation = new Reservation();
            reservation.setSeatId(seatId);
            reservation.setHoldDate(date);
            reservation.setReservationDate(date);
            reservation.setStatus(Reservation.ReservationStatus.HOLD);
            reservation.setUserId(userId);
            reservations.add(reservation);
        }
        return reservations;
    }

    public void confirmReservation(int groupId){
        new ReservationDao().confirmReservation(groupId);
    }
}
