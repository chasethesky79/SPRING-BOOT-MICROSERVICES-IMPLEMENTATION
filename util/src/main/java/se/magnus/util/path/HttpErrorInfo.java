package se.magnus.util.path;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;

@Data
public class HttpErrorInfo {
    private ZonedDateTime timestamp;
    private String path;
    private HttpStatus httpStatus;
    private String message;

    public HttpErrorInfo(final String path, final HttpStatus httpStatus, final String message) {
        this.path = path;
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = ZonedDateTime.now();
    }
}
