package ticketservice.exception;

public class InvalidConfermationException extends Exception {
    private final String errorMessage;

    public InvalidConfermationException(String message) {
        this.errorMessage = message;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
