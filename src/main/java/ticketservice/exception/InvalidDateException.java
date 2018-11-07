package ticketservice.exception;

public class InvalidDateException extends Exception {
    private final String errorMessage;

    public InvalidDateException(String message) {
        this.errorMessage = message;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
