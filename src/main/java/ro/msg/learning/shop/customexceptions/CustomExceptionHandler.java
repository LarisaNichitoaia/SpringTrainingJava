package ro.msg.learning.shop.customexceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.NoSuchObjectException;

@ControllerAdvice
@Builder
@AllArgsConstructor
public class CustomExceptionHandler {
    @ExceptionHandler(value = NoSuchObjectException.class)
    public ResponseEntity<String> handleNoSuchObjectException(NoSuchObjectException ex) {
        String errorMessage = "Error: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}