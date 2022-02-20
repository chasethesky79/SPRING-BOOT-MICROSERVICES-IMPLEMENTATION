package se.magnus.util.path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import se.magnus.util.exceptions.NotFoundException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody HttpErrorInfo handleNotFoundException(
            final ServerHttpRequest request, final Exception ex) {
        return createHttpErrorInfo(NOT_FOUND, request, ex);
    }

    private HttpErrorInfo createHttpErrorInfo(final HttpStatus httpStatus,
                                final ServerHttpRequest request,
                                final Exception ex) {
        final String path = request.getPath().pathWithinApplication().value();
        final String message = ex.getMessage();
        logger.debug("Returning HTTP status: {} for path: {}, message: {}", httpStatus, path, message);
        return new HttpErrorInfo(path, httpStatus, message);
    }
}
