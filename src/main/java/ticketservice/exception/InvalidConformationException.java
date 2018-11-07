package ticketservice.exception;

public class InvalidConformationException extends Exception {
    private final String errorMessage;

    public InvalidConformationException(String message) {
        this.errorMessage = message;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
