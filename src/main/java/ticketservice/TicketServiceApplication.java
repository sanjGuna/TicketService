package ticketservice;

import ticketservice.service.TicketService;
import ticketservice.ui.TicketServiceUI;

/**
 * Ticket service is a demo application to show how a venue can be reserved.
 * This is a console application to demonstrate the following three option with regard to venue reservation.
 * Initially we have a 10X10 venue called Walmart Arena.
 * This application can be extended to handle variable row seat combination.
 *
 * 1.Find available seats
 * 2.Hold best seats for a client based on user selected seats
 * 3.Hold best seats best on star ratings
 * 4.Confirm any reservation using reservation confirmation number;
 * 5.Exit
 */

public class TicketServiceApplication {

    public static void main(String[] args) {
        TicketService.initialize();
        TicketServiceUI.createConsoleUI();
    }

}
