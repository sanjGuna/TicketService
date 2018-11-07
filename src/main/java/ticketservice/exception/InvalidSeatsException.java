package ticketservice.exception;

public class InvalidSeatsException extends Exception {
    private final String errorMessage;

    public InvalidSeatsException(String message) {
        this.errorMessage = message;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
