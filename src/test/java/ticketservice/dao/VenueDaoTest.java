package ticketservice.dao;

import ticketservice.entity.Venue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ticketservice.mockdb.TicketServiceDB;

public class VenueDaoTest {
    private VenueDao venueDao;

    @Before
    public  void setUp() {
        TicketServiceDB.flushDB();
        venueDao = new VenueDao();
    }

    @Test
    public void testCreateDefaultVenue() {
        Venue venue = venueDao.createDefaultVenue("ABC Venue");
        Assert.assertEquals("ABC Venue", venue.getName());
    }

    @Test
    public void testSaveVenue() {
        Venue performanceVenue = new Venue();
        performanceVenue.setName("Test Save venue");
        long venue = venueDao.saveVenue(performanceVenue);
        Assert.assertTrue(venue > 0);

    }

    @Test
    public void testFindValidVenue() {
        Venue performanceVenue = new Venue();
        String venueName = "Test venue";
        performanceVenue.setName(venueName);
        Long venue = venueDao.saveVenue(performanceVenue);
        Venue venue1 = venueDao.findVenue(venue);
        Assert.assertTrue(venue > 0);
        Assert.assertEquals(venueName, venue1.getName());
    }

    @Test
    public void testFindInValidVenue() {
        Venue performanceVenue = new Venue();
        performanceVenue.setName("Test In Find venue");
        long venue = venueDao.saveVenue(performanceVenue);
        Assert.assertTrue(venue > 0);
        Venue venue1 = venueDao.findVenue(-11L);
        Assert.assertNull(venue1);
    }

    @Test
    public void testFindNullVenue() {
        Venue performanceVenue = new Venue();
        performanceVenue.setName("Test Null venue");
        long venue = venueDao.saveVenue(performanceVenue);
        Assert.assertTrue(venue > 0);
        Venue venue1 = venueDao.findVenue(null);
        Assert.assertNull(venue1);
    }
}