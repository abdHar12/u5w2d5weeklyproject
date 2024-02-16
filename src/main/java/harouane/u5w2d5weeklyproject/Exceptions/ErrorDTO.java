package harouane.u5w2d5weeklyproject.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorDTO {
    private String message;
    private LocalDateTime timestamp;
}
