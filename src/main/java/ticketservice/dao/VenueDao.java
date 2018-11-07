package ticketservice.dao;

import java.util.Collection;
import java.util.Date;

import ticketservice.entity.Venue;
import ticketservice.mockdb.TicketServiceDB;

public class VenueDao {

    public Venue createDefaultVenue(String name, String address, Date dateCreated) {
        Venue venue = new Venue();
        venue.setName(name);
        venue.setAddress(address);
        venue.setDateCreated(dateCreated);
        return TicketServiceDB.getVenue(saveVenue(venue));
    }

    public Long saveVenue(Venue venue) {
        return TicketServiceDB.save(venue);
    }

    public Venue findVenue(Long venueId) {
        return TicketServiceDB.getVenue(venueId);
    }

    public Collection<Venue> findAllVenues() {
        return TicketServiceDB.getAllVenues();
    }
}
