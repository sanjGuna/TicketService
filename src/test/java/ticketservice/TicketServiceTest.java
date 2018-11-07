package ticketservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ticketservice.dao.VenueDao;
import ticketservice.dao.ReservationDao;
import ticketservice.dao.SeatDao;
import ticketservice.dao.UserDao;
import ticketservice.entity.Venue;
import ticketservice.entity.Reservation;
import ticketservice.entity.Seat;
import ticketservice.entity.User;
import ticketservice.exception.InvalidConfermationException;
import ticketservice.exception.InvalidDateException;
import ticketservice.exception.InvalidSeatsException;
import ticketservice.exception.InvalidUserException;
import ticketservice.mockdb.TicketServiceDB;
import ticketservice.service.TicketService;

public class TicketServiceTest {

    private TicketService ticketService;
    private ReservationDao reservationDao;


    @Before
    public void setUp() {
        TicketServiceDB.flushDB();
        SeatDao seatDao = new SeatDao();
        UserDao userDao = new UserDao();
        userDao.save(new User("admin", "Admin User"));
        VenueDao venueDao = new VenueDao();
        reservationDao = new ReservationDao();
        Venue venue = venueDao.createDefaultVenue("ABC Venue");
        seatDao.createDefaultSeats(venue, 2, 3);
        ticketService = new TicketService();

    }

    @Test
    public void testExpireReservationsRemoved() {
        List<Reservation> reservations = new ArrayList<>();
        Reservation expiredReservation = createReservation(20000, 1, 1L);
        reservations.add(expiredReservation);
        reservationDao.saveReservation(reservations);

        Map<Long, Seat> availableSeats;
        try {
            availableSeats = ticketService.findAvailableSeats(new Date());
            Assert.assertEquals(6, availableSeats.keySet().size());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testExpireReservationsActiveWillNotReturnAsAvailable() {
        List<Reservation> reservations = new ArrayList<>();
        Reservation activeReservation = createReservation(0, 1, 2L);
        reservations.add(activeReservation);
        reservationDao.saveReservation(reservations);

        Map<Long, Seat> availableSeats;
        try {
            availableSeats = ticketService.findAvailableSeats(new Date());
            Assert.assertEquals(5, availableSeats.keySet().size());
        } catch (Exception e) {
            Assert.fail();
        }
    }


    @Test
    public void testSeatHoldWithBadDate() {
        try {
            ticketService.holdSeat(getDaysInDays(-7), new ArrayList<>(), "admin");
        } catch (InvalidDateException e) {
            Assert.assertEquals("Invalid Date. You can only see availability for Future dates", e.getMessage());
        } catch (InvalidSeatsException | InvalidUserException e) {
            Assert.fail();
        }
    }

    @Test
    public void testSeatHoldWithBadUserName() {
        try {
            ticketService.holdSeat(new Date(), new ArrayList<>(), "admin123");
        } catch (InvalidUserException e) {
            Assert.assertEquals("Invalid User Id", e.getMessage());
        } catch (InvalidSeatsException | InvalidDateException e) {
            Assert.fail();
        }
    }

    @Test
    public void testSeatHoldWithInvalidSeats() {
        try {
            ticketService.holdSeat(new Date(), new ArrayList<>(), "admin");
        } catch (InvalidSeatsException e) {
            Assert.assertEquals("Invalid seat ids", e.getMessage());
        } catch (InvalidUserException | InvalidDateException e) {
            Assert.fail();
        }
    }


    @Test
    public void testSeatHoldWithUnAvailableSeats() {
        List<Reservation> reservations = new ArrayList<>();
        Reservation activeReservation = createReservation(0, 1, 2L);
        reservations.add(activeReservation);
        reservationDao.saveReservation(reservations);
        try {
            ticketService.holdSeat(new Date(), Arrays.asList(1L, 2L), "admin");
        } catch (InvalidSeatsException e) {
            Assert.assertEquals("Some or all seat ids are not available", e.getMessage());
        } catch (InvalidUserException | InvalidDateException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testSeatHoldWithAvailableSeats() {
        List<Reservation> reservations = new ArrayList<>();
        Reservation activeReservation = createReservation(0, 1, 2L);
        reservations.add(activeReservation);
        reservationDao.saveReservation(reservations);
        try {
            Integer confirmationId = ticketService.holdSeat(getDaysInDays(1), Arrays.asList(1L, 2L), "admin");
            Assert.assertEquals(2, (int) confirmationId);
        } catch (InvalidSeatsException e) {
            Assert.assertEquals("Some or all seat ids are not available", e.getMessage());
        } catch (InvalidUserException | InvalidDateException e) {
            Assert.fail(e.getMessage());
        }
    }


    @Test
    public void testConfirmSeats() {
        List<Reservation> reservations = new ArrayList<>();
        Reservation activeReservation = createReservation(0, 1, 2L);
        reservations.add(activeReservation);
        reservationDao.saveReservation(reservations);
        try {
            boolean success = ticketService.confirmReservation(1);
            Assert.assertTrue(success);
        }  catch (InvalidConfermationException e) {
            Assert.fail();
        }
    }

    @Test
    public void testConfirmSeatsWithInvalidIdFailds() {
        List<Reservation> reservations = new ArrayList<>();
        Reservation activeReservation = createReservation(0, 1, 2L);
        reservations.add(activeReservation);
        reservationDao.saveReservation(reservations);
        try {
            ticketService.confirmReservation(3);
        }  catch (InvalidConfermationException e) {
            Assert.assertEquals("Conformation number is not valid", e.getMessage());
        }
    }

    @Test
    public void testExpireReservationsException() {
        Date sevenDaysAgo = getDaysInDays(-7);
        try {
            ticketService.findAvailableSeats(sevenDaysAgo);
        } catch (InvalidDateException e) {
            Assert.assertEquals("Invalid Date. You can only see availability for Future dates", e.getMessage());
        }
    }

    private Date getDaysInDays(int daysAgo) {
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, daysAgo);
        return cal.getTime();
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