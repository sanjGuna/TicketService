package ticketservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ticketservice.dao.PerformancesVenueDao;
import ticketservice.dao.ReservationDao;
import ticketservice.dao.SeatDao;
import ticketservice.entity.PerformenceVenue;
import ticketservice.entity.Reservation;
import ticketservice.entity.Seat;
import ticketservice.exception.InvalidDateException;
import ticketservice.mockdb.TicketServiceDB;
import ticketservice.service.TicketService;

public class TicketServiceTest {

    private TicketService ticketService;
    private ReservationDao reservationDao;


    @Before
    public void setUp() {
        TicketServiceDB.flushDB();
        SeatDao seatDao = new SeatDao();
        PerformancesVenueDao performancesVenueDao = new PerformancesVenueDao();
        reservationDao = new ReservationDao();
        PerformenceVenue venue = performancesVenueDao.createDefaultVenue("ABC Venue");
        seatDao.createDefaultSeats(venue, 2, 3);
        ticketService = new TicketService();

    }

    @Test
    public void testExpireReservationsRemoved() {
        List<Reservation> reservations = new ArrayList<>();
        Reservation expiredReservation = createReservation(20000, 1, 1L);
        reservations.add(expiredReservation);
        reservationDao.saveReservation(reservations);

        Map<Long, Seat> availableSeats = null;
        try {
            availableSeats = ticketService.findAvailableSeats(new Date());
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertEquals(6, availableSeats.keySet().size());
    }

    @Test
    public void testExpireReservationsActiveWillNotReturnAsAvailable() {
        List<Reservation> reservations = new ArrayList<>();
        Reservation activeReservation = createReservation(0, 1, 2L);
        reservations.add(activeReservation);
        reservationDao.saveReservation(reservations);

        Map<Long, Seat> availableSeats = null;
        try {
            availableSeats = ticketService.findAvailableSeats(new Date());
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertEquals(5, availableSeats.keySet().size());
    }


    @Test
    public void testExpireReservationsException() {
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, -7);
        Date sevenDaysAgo = cal.getTime();
        try {
            ticketService.findAvailableSeats(sevenDaysAgo);
        } catch (InvalidDateException e) {
            Assert.assertEquals("Invalid Date. You can only see availability for Future dates", e.getMessage());
        }
    }

    private Reservation createReservation(int delay, int groupId, Long seatId) {
        Reservation reservation = new Reservation();
        reservation.setReservationGroupId(groupId);
        reservation.setReservationDate(new Date(System.currentTimeMillis() - delay));
        reservation.setHoldDate(new Date(System.currentTimeMillis() - delay));
        reservation.setSeatId(seatId);
        return reservation;
    }

}