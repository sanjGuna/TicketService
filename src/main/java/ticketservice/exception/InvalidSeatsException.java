package ticketservice.exception;

public class InvalidUserException extends Exception {
    private final String errorMessage;

    public InvalidUserException(String message) {
        this.errorMessage = message;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
