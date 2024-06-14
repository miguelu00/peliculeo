package exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final Long serialVersionUID = 1L;
    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public ResourceNotFoundException(String message, Throwable cause, String resourceName, String fieldName, String fieldValue) {
        super(String.format("RECURSO %s NO ENCONTRADO! key: %s : %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
