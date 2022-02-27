package se.magnus.util.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundException extends RuntimeException {
  private String message;
  private Throwable cause;

  public NotFoundException(final String message) {
    this.message = message;
  }
}
