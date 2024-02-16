package harouane.u5w2d5weeklyproject.Exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException{
    List<ObjectError> objectErrorList;
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(List<ObjectError> objectErrorList){
        super("Ci sono i seguenti errori:");
        this.objectErrorList=objectErrorList;
    }

}
