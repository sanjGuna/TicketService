package ticketservice.entity;


import java.util.Date;

public class Reservation {
    private Long reservationId;
    private Long seatId;
    private String userId;
    private Date holdDate;
    private Date reservationDate;
    private int reservationGroupId;
    private ReservationStatus status;

    public enum ReservationStatus {
        HOLD,
        CONFIRMED
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getHoldDate() {
        return holdDate;
    }

    public void setHoldDate(Date holdDate) {
        this.holdDate = holdDate;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getReservationGroupId() {
        return reservationGroupId;
    }

    public void setReservationGroupId(int reservationGroupId) {
        this.reservationGroupId = reservationGroupId;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
