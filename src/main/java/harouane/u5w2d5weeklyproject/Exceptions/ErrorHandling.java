package harouane.u5w2d5weeklyproject.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ErrorHandling {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handlingBadRequest(BadRequestException ex){
        if(ex.objectErrorList!=null){
            List<String> errors=ex.getObjectErrorList().stream().map(err-> err.getDefaultMessage()).toList();
            return new ErrorWithListDTO(ex.getMessage(), LocalDateTime.now(), errors);
        }
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());

    }
    @ExceptionHandler(NotFoundElements.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handlingNotFoundElements(NotFoundElements ex) {
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handlingException(Exception ex) {
        ex.printStackTrace();
        return new ErrorDTO("Il problema verrà risolto", LocalDateTime.now());
    }
}
