package ticketservice.dao;

import java.util.Map;

import ticketservice.entity.PerformenceVenue;
import ticketservice.entity.Seat;
import ticketservice.mockdb.TicketServiceDB;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SeatDaoTest {
    private static SeatDao seatDao;
    private static PerformancesVenueDao performancesVenueDao;
    private static Map<Long, Seat> seatMap;

    @BeforeClass
    public static void setUp() {
        TicketServiceDB.flushDB();
        seatDao = new SeatDao();
        performancesVenueDao = new PerformancesVenueDao();
        PerformenceVenue venue = performancesVenueDao.createDefaultVenue("ABC Venue");
        seatDao.createDefaultSeats(venue, 2, 3);
        seatMap = TicketServiceDB.getSeatsTable();

    }

    @Test
    public void testTotalSeatCreatedIs6() {
        Assert.assertEquals(6, seatMap.keySet().size());
    }

    @Test
    public void testSeatOneIsForRowOneLocationOne() {
        Seat seat = seatMap.get(1L);
        Assert.assertEquals(1, (int) seat.getRowNumber());
        Assert.assertEquals(1, (int) seat.getSeatLocationNumber());
    }

    @Test
    public void testSecondRowFirstSeatIsLocation1() {
        Seat seat = seatMap.get(4L);
        Assert.assertEquals(2, (int) seat.getRowNumber());
        Assert.assertEquals(1, (int) seat.getSeatLocationNumber());
    }

    @Test
    public void testFindValidSeat() {
        Seat seat = seatDao.find(4L);
        Assert.assertEquals(2, (int) seat.getRowNumber());
        Assert.assertEquals(1, (int) seat.getSeatLocationNumber());
    }

    @Test
    public void testFindInValidSeat() {
        Seat seat = seatDao.find(-4L);
        Assert.assertNull(seat);
    }

}