package ticketservice.dao;

import ticketservice.entity.Venue;
import ticketservice.mockdb.TicketServiceDB;

public class VenueDao {

    public Venue createDefaultVenue(String venueName) {
        Venue venue = new Venue();
        venue.setName(venueName);
        return TicketServiceDB.getVenue(saveVenue(venue));
    }

    public Long saveVenue(Venue venue) {
        return TicketServiceDB.save(venue);
    }

    public Venue findVenue(Long venueId) {
        return TicketServiceDB.getVenue(venueId);

    }

}
