package ticketservice.dao;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ticketservice.entity.Reservation;
import ticketservice.entity.Venue;
import ticketservice.mockdb.TicketServiceDB;

public class ReservationDaoTest {
    private ReservationDao reservationDao;

    @Before
    public void setUp() {
        TicketServiceDB.flushDB();
        SeatDao seatDao = new SeatDao();
        VenueDao venueDao = new VenueDao();
        reservationDao = new ReservationDao();
        Venue venue = venueDao.createDefaultVenue("ABC Venue", "1 Main St", new Date());
        seatDao.createDefaultSeats(venue, 2, 3);

    }

    @Test
    public void testTotalReservationBatch() {

        List<Reservation> reservations = new ArrayList<>();
        Reservation reservation = new Reservation();
        reservation.setReservationGroupId(1);
        reservation.setReservationDate(new Date(System.currentTimeMillis()));
        reservation.setHoldDate(new Timestamp(System.currentTimeMillis()));
        reservation.setStatus(Reservation.ReservationStatus.HOLD);
        reservation.setSeatId(1L);

        reservations.add(reservation);

        Reservation reservation2 = new Reservation();
        reservation2.setReservationGroupId(1);
        reservation2.setReservationDate(new Date(System.currentTimeMillis()));
        reservation2.setHoldDate(new Timestamp(System.currentTimeMillis()));
        reservation2.setSeatId(4L);
        reservations.add(reservation2);


        reservationDao.saveReservation(reservations);
        Reservation reservation1 = reservationDao.findReservation(1L);
        Reservation reservation3 = reservationDao.findReservation(2L);
        Assert.assertEquals(1L, (long) reservation1.getReservationId());
        Assert.assertEquals(2, (long) reservation3.getReservationId());
        Assert.assertEquals(1, reservation1.getReservationGroupId());
        Assert.assertEquals(1, reservation3.getReservationGroupId());


    }

}