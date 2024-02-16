package harouane.u5w2d5weeklyproject.DTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DevicePayload {
    @NotEmpty(message = "Il tipo di device è obbligatorio")
    String type;
    @NotEmpty(message = "Lo stato del device è obbligatorio")
    String state;
}

