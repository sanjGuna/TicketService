package ticketservice.dao;

import ticketservice.entity.PerformenceVenue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ticketservice.mockdb.TicketServiceDB;

public class PerformancesVenueDaoTest {
    private  PerformancesVenueDao performancesVenueDao;

    @Before
    public  void setUp() {
        TicketServiceDB.flushDB();
        performancesVenueDao = new PerformancesVenueDao();
    }

    @Test
    public void testCreateDefaultVenue() {
        PerformenceVenue venue = performancesVenueDao.createDefaultVenue("ABC Venue");
        Assert.assertEquals("ABC Venue", venue.getName());
    }

    @Test
    public void testSaveVenue() {
        PerformenceVenue performenceVenue = new PerformenceVenue();
        performenceVenue.setName("Test Save venue");
        long venue = performancesVenueDao.saveVenue(performenceVenue);
        Assert.assertTrue(venue > 0);

    }

    @Test
    public void testFindValidVenue() {
        PerformenceVenue performenceVenue = new PerformenceVenue();
        String venueName = "Test venue";
        performenceVenue.setName(venueName);
        Long venue = performancesVenueDao.saveVenue(performenceVenue);
        PerformenceVenue venue1 = performancesVenueDao.findVenue(venue);
        Assert.assertTrue(venue > 0);
        Assert.assertEquals(venueName, venue1.getName());
    }

    @Test
    public void testFindInValidVenue() {
        PerformenceVenue performenceVenue = new PerformenceVenue();
        performenceVenue.setName("Test In Find venue");
        long venue = performancesVenueDao.saveVenue(performenceVenue);
        Assert.assertTrue(venue > 0);
        PerformenceVenue venue1 = performancesVenueDao.findVenue(-11L);
        Assert.assertNull(venue1);
    }

    @Test
    public void testFindNullVenue() {
        PerformenceVenue performenceVenue = new PerformenceVenue();
        performenceVenue.setName("Test Null venue");
        long venue = performancesVenueDao.saveVenue(performenceVenue);
        Assert.assertTrue(venue > 0);
        PerformenceVenue venue1 = performancesVenueDao.findVenue(null);
        Assert.assertNull(venue1);
    }
}