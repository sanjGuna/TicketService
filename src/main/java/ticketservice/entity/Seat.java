package ticketservice.entity;

public class Seat {
    private Long seatId;
    private Integer rowNumber;
    private Integer seatLocationNumber;
    private Float price;
    private Long venueId;
    private int rating;

    public Seat() {
    }

    public Seat(Long seatId, Integer rowNumber, Integer seatLocationNumber, Float price, Long venueId, int rating) {
        this.seatId = seatId;
        this.rowNumber = rowNumber;
        this.seatLocationNumber = seatLocationNumber;
        this.price = price;
        this.venueId = venueId;
        this.rating = rating;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer getSeatLocationNumber() {
        return seatLocationNumber;
    }

    public void setSeatLocationNumber(Integer seatLocationNumber) {
        this.seatLocationNumber = seatLocationNumber;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
