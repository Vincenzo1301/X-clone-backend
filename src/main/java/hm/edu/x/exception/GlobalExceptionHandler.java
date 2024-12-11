package hm.edu.x.exception;

import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  protected ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    ProblemDetail problem =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    problem.setTitle("Bad request.");
    problem.setType(obtainType());

    logger.error(ex.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
  }

  @ExceptionHandler(value = {BadRequestException.class})
  protected ResponseEntity<ProblemDetail> handleBadRequestException(BadRequestException ex) {
    ProblemDetail problem =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    problem.setTitle("Bad request.");
    problem.setType(obtainType());

    logger.error(ex.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
  }

  private URI obtainType() {
    return ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
  }
}
