package ppss.excepciones;

public class IsbnInvalidoException extends Exception {
    public IsbnInvalidoException() {
    }
    public IsbnInvalidoException(String message) {
        super(message);
    }
}
