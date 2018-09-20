package database.exception;

public class WrongConnection extends RuntimeException {

    public WrongConnection() {
        super("Unable open connection");
    }

    public WrongConnection(String message) {
        super(message);
    }

}
