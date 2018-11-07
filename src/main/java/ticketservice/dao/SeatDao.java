package ticketservice.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ticketservice.entity.Venue;
import ticketservice.entity.Seat;
import ticketservice.mockdb.TicketServiceDB;

public class SeatDao {


    public void createDefaultSeats(Venue venue, int rows, int columns) {
        List<Seat> seats = new ArrayList<>();
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= columns; col++) {
                Seat seat = new Seat();
                seat.setSeatLocationNumber(col);
                seat.setPrice(1 * 10f);
                seat.setVenueId(venue.getVenueId());
                seat.setRowNumber(row);
                seat.setRating(5-(col % 5));
                seats.add(seat);
            }
        }
        saveBatch(seats);
    }

    private void saveBatch(List<Seat> seats) {
        TicketServiceDB.saveSeatBatch(seats);
    }

    public Seat find(Long seatId) {
        return TicketServiceDB.getSeat(seatId);
    }

    public Map<Long, Seat> findAllSeats() {
        return TicketServiceDB.getSeatsTable();
    }
}
