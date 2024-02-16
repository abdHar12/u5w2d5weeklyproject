package harouane.u5w2d5weeklyproject.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public record EmployeePayload(
        @NotEmpty(message = "L'username è obbligatorio")
        @Size(min = 3, max = 20, message = "L'username deve avere tra 3 e 8 caratteri")
         String username,
        @NotEmpty(message = "L'Email è obbligatoria")
        @Email(message = "Non hai inserito un'email valida")
         String email,
        @NotEmpty(message = "Il name è obbligatorio")
        @Size(min = 3, max = 20, message = "Il name deve avere tra 3 e 8 caratteri")
         String name,
        @NotEmpty(message = "Il surname è obbligatorio")
        @Size(min = 3, max = 20, message = "Il surname deve avere tra 3 e 8 caratteri")
         String surname){

}
