package harouane.u5w2d5weeklyproject.Exceptions;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ErrorWithListDTO extends ErrorDTO{
    List<String> errors;

    public ErrorWithListDTO(String message, LocalDateTime timestamp, List<String> errors) {
        super(message, timestamp);
        this.errors=errors;
    }
}
