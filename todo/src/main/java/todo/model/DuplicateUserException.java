package todo.model;

public class DuplicateUserException extends RuntimeException{

	   public DuplicateUserException(String message) {
	        super(message);
	    }
}
