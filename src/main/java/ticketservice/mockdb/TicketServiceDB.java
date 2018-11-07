package ticketservice.mockdb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ticketservice.entity.PerformenceVenue;
import ticketservice.entity.Reservation;
import ticketservice.entity.Seat;
import ticketservice.entity.User;

/**
 * This is a mock database. This will act like Ticket reservation entity base that has
 * VENUE, SEAT and RESERVATION tables. These methods are DB calls that we should be using with SQL.
 */

public class TicketServiceDB {
    private static final Map<Long, PerformenceVenue> PERFORMANCE_VENUE_TABLE = new HashMap<>();
    private static final Map<Long, Seat> SEAT_TABLE = new HashMap<>();
    private static final Map<Long, Reservation> RESERVATION_TABLE = new HashMap<>();
    private static final Map<String, User> USER = new HashMap<>();
    private static final Long EXPIRATION_IN_MS = 10000L;
    private static Long currentVenueSequenceId = 0L;
    private static Long currentSeatSequenceId = 0L;
    private static Long currentReservationSequenceId = 0L;
    private static Integer currentReservationGroupSequenceId = 0;

    private TicketServiceDB() {
    }

    public static Long save(PerformenceVenue performenceVenue) {
        PERFORMANCE_VENUE_TABLE.put(++currentVenueSequenceId, performenceVenue);
        performenceVenue.setVenueId(currentVenueSequenceId);
        return currentVenueSequenceId;
    }

    public static PerformenceVenue getVenue(Long venueId) {
        return PERFORMANCE_VENUE_TABLE.get(venueId);
    }

    public static String save(User user){
        USER.put(user.getUserId(), user);
        return user.getUserId();
    }

    public static User getUser(String userId){
        return USER.get(userId);
    }


    public static void saveSeatBatch(List<Seat> seats) {
        for (Seat seat : seats) {
            seat.setSeatId(++currentSeatSequenceId);
            SEAT_TABLE.put(currentSeatSequenceId, seat);
        }
    }

    public static Integer saveReservationBatch(List<Reservation> reservations) {
        Integer groupId = ++currentReservationGroupSequenceId;
        for (Reservation reservation : reservations) {
            reservation.setReservationId(++currentReservationSequenceId);
            reservation.setReservationGroupId(groupId);
            RESERVATION_TABLE.put(currentReservationSequenceId, reservation);
        }
        return groupId;

    }

    public static Seat getSeat(Long seatNumber) {
        return SEAT_TABLE.get(seatNumber);
    }

    public static Map<Long, Seat> getSeatsTable() {
        return SEAT_TABLE;
    }


    public static Reservation getReservation(Long resrvationId) {
        return RESERVATION_TABLE.get(resrvationId);
    }

    public static List<Reservation> getReservationByDate(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        List<Reservation> reservations = new ArrayList<>();
        for (Reservation value : RESERVATION_TABLE.values()) {
            if (fmt.format(value.getReservationDate()).equals(fmt.format(date))) {
                reservations.add(value);
            }
        }
        return reservations;

    }

    public static void expireReservations() {
        List<Long> expire = new ArrayList<>();
        for (Reservation value : RESERVATION_TABLE.values()) {
            if ((System.currentTimeMillis() - value.getHoldDate().getTime()) >= EXPIRATION_IN_MS) {
                expire.add(value.getReservationId());
            }
        }
        for (Long reservationId : expire) {
            RESERVATION_TABLE.remove(reservationId);
        }
    }

    public static void confirmReservation(int groupId) {
        for (Reservation value : RESERVATION_TABLE.values()) {
            if (value.getReservationGroupId() == groupId) {
                value.setStatus(Reservation.ReservationStatus.CONFIRMED);
            }
        }
    }

    public static void flushDB() {
        RESERVATION_TABLE.clear();
        PERFORMANCE_VENUE_TABLE.clear();
        SEAT_TABLE.clear();
        currentVenueSequenceId = 0L;
        currentSeatSequenceId = 0L;
        currentReservationSequenceId = 0L;
        currentReservationGroupSequenceId = 0;

    }
}
