package ua.training.utils.validation;

public class ValidationError {
    /**
     * Field on which validation failed
     */
    private String field;
    /**
     * Message key in properties of en error type
     */
    private String message;

    public ValidationError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
