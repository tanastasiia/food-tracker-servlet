package ua.training.utils.validation;


import java.util.List;

public class ValidationException extends Exception {
    private List<ValidationError> errors;

    public ValidationException(List<ValidationError> errors) {
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

}
