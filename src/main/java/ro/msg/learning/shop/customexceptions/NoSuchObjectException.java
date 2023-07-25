package ro.msg.learning.shop.customexceptions;

public class NoSuchObjectException extends RuntimeException {
    public NoSuchObjectException(String message) {
        super(message);
    }
}
