package dio.exploringDesignPatterns.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidCepException extends RuntimeException {
  public InvalidCepException() {
    super("CEP invalido!");
  }
}
