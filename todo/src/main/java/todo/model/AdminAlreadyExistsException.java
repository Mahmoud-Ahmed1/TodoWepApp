package todo.model;

public class AdminAlreadyExistsException extends RuntimeException{
    public AdminAlreadyExistsException(String message) {
        super(message);
    }

}
