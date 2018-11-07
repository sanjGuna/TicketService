package ticketservice.dao;

import ticketservice.entity.PerformenceVenue;
import ticketservice.mockdb.TicketServiceDB;

public class PerformancesVenueDao {

    public PerformenceVenue createDefaultVenue(String venueName) {
        PerformenceVenue performenceVenue = new PerformenceVenue();
        performenceVenue.setName(venueName);
        return TicketServiceDB.getVenue(saveVenue(performenceVenue));
    }

    public Long saveVenue(PerformenceVenue performenceVenue) {
        return TicketServiceDB.save(performenceVenue);
    }

    public PerformenceVenue findVenue(Long venueId) {
        return TicketServiceDB.getVenue(venueId);

    }

}
