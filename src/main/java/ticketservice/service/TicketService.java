package ticketservice.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import ticketservice.dao.ReservationDao;
import ticketservice.dao.SeatDao;
import ticketservice.dao.UserDao;
import ticketservice.dao.VenueDao;
import ticketservice.entity.Reservation;
import ticketservice.entity.Seat;
import ticketservice.entity.User;
import ticketservice.entity.Venue;
import ticketservice.exception.InvalidConfermationException;
import ticketservice.exception.InvalidDateException;
import ticketservice.exception.InvalidSeatsException;
import ticketservice.exception.InvalidUserException;

public class TicketService {
    private static final Integer DEFAULT_ROWS = 10;
    private static final Integer DEFAULT_COLUMNS = 10;

    /**
     * Initializing Venue seating for demo purpose
     */
    public static void initialize() {
        VenueDao venueDao = new VenueDao();
        SeatDao seatDao = new SeatDao();
        UserDao userDao = new UserDao();
        Venue dramaVenue = venueDao.createDefaultVenue("Drama Arena", "1 Main St", new Date());
        Venue comedyVenue = venueDao.createDefaultVenue("Comedy Arena", "1 Main St", new Date());
        seatDao.createDefaultSeats(dramaVenue, DEFAULT_ROWS, DEFAULT_COLUMNS);
        seatDao.createDefaultSeats(comedyVenue, DEFAULT_ROWS, DEFAULT_COLUMNS);
        userDao.save(new User("admin", "Admin User"));
        userDao.save(new User("testuser", "Test User"));
    }

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

    public Integer holdSeat(Date date, List<Long> seatIds, String userId) throws InvalidDateException,
            InvalidSeatsException, InvalidUserException {
        validateDate(date);
        validateUser(userId);
        validateSeatAvailability(seatIds, date);
        List<Reservation> reservations = buildReservations(date, seatIds, userId);
        return new ReservationDao().saveReservation(reservations);
    }

    public boolean confirmReservation(int groupId) throws InvalidConfermationException {
        boolean success = new ReservationDao().confirmReservation(groupId);
        if (!success) {
            throw new InvalidConfermationException("Conformation number is not valid");
        }
        return success;
    }

    private void validateDate(Date date) throws InvalidDateException {
        if (date.before(new Date())) {
            throw new InvalidDateException("Invalid Date. You can only see availability for Future dates");
        }
    }


    private void validateSeatAvailability(List<Long> seatIds, Date date) throws InvalidSeatsException {
        boolean isSeatAvailable = new ReservationDao().isSeatAvailable(seatIds, date);
        if (!isSeatAvailable) {
            throw new InvalidSeatsException("Some or all seat ids are not available");
        }
    }

    private void validateUser(String userId) throws InvalidUserException {
        User user = new UserDao().find(userId);
        if (user == null) {
            throw new InvalidUserException("Invalid User Id");
        }
    }

    private List<Reservation> buildReservations(Date date, List<Long> seatIds, String userId) throws InvalidSeatsException {
        if (seatIds == null || seatIds.isEmpty()) {
            throw new InvalidSeatsException("Invalid seat ids");
        }
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


}
