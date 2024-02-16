package harouane.u5w2d5weeklyproject.DTOs.DeviceDTOs;

import harouane.u5w2d5weeklyproject.Entities.Employee;
import harouane.u5w2d5weeklyproject.Enums.DeviceState;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreationDevicePayload{
    @NotEmpty(message = "Il tipo di device è obbligatorio")
    String type;
    @NotEmpty(message = "Lo stato del device è obbligatorio")
    String state;
}

