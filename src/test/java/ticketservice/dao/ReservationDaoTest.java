package ticketservice.dao;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ticketservice.entity.Venue;
import ticketservice.entity.Reservation;
import ticketservice.entity.Seat;
import ticketservice.mockdb.TicketServiceDB;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReservationDaoTest {
    private  SeatDao seatDao;
    private VenueDao venueDao;
    private  ReservationDao reservationDao;
    private  Map<Long, Seat> seatMap;

    @Before
    public  void setUp() {
        TicketServiceDB.flushDB();
        seatDao = new SeatDao();
        venueDao = new VenueDao();
        reservationDao = new ReservationDao();
        Venue venue = venueDao.createDefaultVenue("ABC Venue");
        seatDao.createDefaultSeats(venue, 2, 3);
        seatMap = TicketServiceDB.getSeatsTable();

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
        Assert.assertEquals(1L, (long)reservation1.getReservationId());
        Assert.assertEquals(2, (long)reservation3.getReservationId());
        Assert.assertEquals(1, reservation1.getReservationGroupId());
        Assert.assertEquals(1, reservation3.getReservationGroupId());


    }

}