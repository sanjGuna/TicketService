package ticketservice.entity;

import java.util.Date;

public class PerformenceVenue {
    private Long  venueId;
    private String name;
    private String address;
    private Date dateCreated;
    private Long createdBy;


    public PerformenceVenue() {
    }

    public PerformenceVenue(Long venueId, String name, String address, Date dateCreated, Long createdBy) {
        this.venueId = venueId;
        this.name = name;
        this.address = address;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}
