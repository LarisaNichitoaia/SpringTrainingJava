package ro.msg.learning.shop.controller.customexceptions;

public class NoSuchObjectException extends RuntimeException {
    public NoSuchObjectException(String message) {
        super(message);
    }
}
