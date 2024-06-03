package dev.bensantosa.runners.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * RunNotFoundException
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RunNotFoundException extends RuntimeException {
  public RunNotFoundException() {
    super("Run Not Found");
  }

}
