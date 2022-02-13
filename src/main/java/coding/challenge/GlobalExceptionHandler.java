package coding.challenge;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException e
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(
                                     new ErrorResponse(
                                             e.getConstraintViolations()
                                              .stream()
                                              .map(cv -> cv.getPropertyPath().toString() +" "+ cv.getMessage()+".")
                                              .collect(Collectors.joining())
                                     )
                             );
    }
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatus(
            ResponseStatusException e
    ) {
        return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(e.getReason()));
    }
}
