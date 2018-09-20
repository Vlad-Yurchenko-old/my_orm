package database.exception;

public class InitializrException extends RuntimeException {

    public InitializrException() {
        super("Unable initialize connector");
    }

    public InitializrException(String message) {
        super(message);
    }

}
