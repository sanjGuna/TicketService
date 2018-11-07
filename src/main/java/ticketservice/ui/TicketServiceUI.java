package ticketservice.ui;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import ticketservice.dao.SeatDao;
import ticketservice.entity.Seat;
import ticketservice.exception.InvalidDateException;
import ticketservice.service.TicketService;

/**
 * TicketServiceUI is a simple java console based class to get input parameters and display the results.
 * Since we do not have a GUI/DB/Webservice base implementation, this class will be a utility
 * to get request and response data to user.
 */
public class TicketServiceUI {
    private TicketServiceUI() {
    }

    public static void createConsoleUI() {
        Console console = System.console();
        printInitialSeatAvailability(console);
        printOptions(console);
        console.readLine("\nThank you for using Ticket Service.");
    }

    private static void printInitialSeatAvailability(Console console) {
        Map<Long, Seat> allSeats = new SeatDao().findAllSeats();
        printSeats(console, allSeats);
    }

    private static void printSeats(Console console, Map<Long, Seat> seatMap) {
        int currRow = 0;
        for (Long aLong : seatMap.keySet()) {
            if (seatMap.get(aLong).getRowNumber() != currRow) {
                currRow = seatMap.get(aLong).getRowNumber();
                console.printf("\n");
                console.printf("Row " + currRow + " : " + aLong + "(" + seatMap.get(aLong).getRating() + ("*) "));

            } else {
                console.printf(aLong + "(" + seatMap.get(aLong).getRating() + ("*) "));
            }
        }
    }

    private static void printOptions(Console console) {
        console.printf("\nWhat would you like to do\n");
        console.printf("A. Find Available Seats\n");
        console.printf("B. Hold seat for a client\n");
        console.printf("C. Confirm Booking\n");
        console.printf("D. Exit\n");

        String option = console.readLine();

        switch (option.toUpperCase()) {
            case "A":
                getAvailableSeats(console);
                break;
            case "B":
                holdSeats(console);
                break;
            case "C":
                confirmReservation(console);
                break;
            case "D":
                System.exit(0);
                break;
            default:
                invalidOption(console);
                break;
        }
    }

    private static void invalidOption(Console console) {
        console.printf("Invalid Option");
    }


    private static void getAvailableSeats(Console console) {
        TicketService ticketService = new TicketService();
        console.printf("please enter date(dd/MM/yyyy): ");
        String date = console.readLine();
        try {
            Map<Long, Seat> availableSeats = ticketService.
                    findAvailableSeats(new SimpleDateFormat("dd/MM/yyyy").parse(date));
            printSeats(console, availableSeats);

        } catch (InvalidDateException e) {
            console.printf(e.getMessage());
            console.printf("\n");
        } catch (ParseException e) {
            console.printf(e.getMessage());
            System.exit(-1);
        }
        printOptions(console);
    }

    private static void holdSeats(Console console) {
        TicketService ticketService = new TicketService();
        console.printf("please enter userId");
        String userId = console.readLine();
        console.printf("please enter date(dd/MM/yyyy): ");
        String date = console.readLine();
        try {
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            console.printf("please enter comma separated seat ids ");
            String seats = console.readLine();
            List<Long> seatsIds = new ArrayList<>();
            for (String s : seats.split("\\s*,\\s*")) {
                seatsIds.add(Long.parseLong(s));
            }
            int reservationId = ticketService.holdSeat(date1, seatsIds, userId);
            console.printf(" Your reservation was successful. Reservation number is : " + reservationId);
            console.printf(" Use this reservation id to confirm reservation ");
            printOptions(console);
        } catch (InvalidDateException e) {
            console.printf(e.getMessage());
            console.printf("\n");
        } catch (ParseException e) {
            console.printf(e.getMessage());
            System.exit(-1);
        }
        printOptions(console);
    }


    private static void confirmReservation(Console console) {
        TicketService ticketService = new TicketService();
        console.printf("please enter reservation number: ");
        String groupId = console.readLine();
        ticketService.confirmReservation(Integer.parseInt(groupId));
        console.printf("Your Reservation is Confirmed\n");
        printOptions(console);

    }

}
