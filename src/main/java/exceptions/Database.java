package exceptions;

public class Database extends RuntimeException {
    public Database(String message) {
        super(message);
    }
}
