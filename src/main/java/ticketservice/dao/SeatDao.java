package ticketservice.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ticketservice.entity.Seat;
import ticketservice.entity.Venue;
import ticketservice.mockdb.TicketServiceDB;
/**
 * Data Access class to access seat table
 */
public class SeatDao {
    /**
     * Create Default seating in a rectangular arena.
     * This system is capable of handling variable seating counts in a row.
     * We identify best seats based on start rating that was pre-populated.
     * Demo mode will have mode by 5 as start rating.
     * @param venue venue object
     * @param rows row count
     * @param columns column count
     */
    public void createDefaultSeats(Venue venue, int rows, int columns) {
        List<Seat> seats = new ArrayList<>();
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= columns; col++) {
                Seat seat = new Seat();
                seat.setSeatLocationNumber(col);
                seat.setPrice(1 * 10f);
                seat.setVenueId(venue.getVenueId());
                seat.setRowNumber(row);
                seat.setRating(5 - (col % 5));
                seats.add(seat);
            }
        }
        saveBatch(seats);
    }

    /**
     * Save Seats as a bulk. Thi will be used when saving all seats for a venue.
     * @param seats list of seats
     */
    private void saveBatch(List<Seat> seats) {
        TicketServiceDB.saveSeatBatch(seats);
    }

    /**
     * Find a seat by given seat ud
     * @param seatId seat id
     * @return a seat
     */
    public Seat find(Long seatId) {
        return TicketServiceDB.getSeat(seatId);
    }

    public Map<Long, Seat> findAllSeats() {
        return TicketServiceDB.getSeatsTable();
    }
}
