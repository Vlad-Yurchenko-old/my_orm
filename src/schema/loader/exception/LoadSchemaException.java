package schema.loader.exception;

public class LoadSchemaException extends RuntimeException {

    public LoadSchemaException() {
        super("Unable load schema exception");
    }

    public LoadSchemaException(String message) {
        super(message);
    }

}
