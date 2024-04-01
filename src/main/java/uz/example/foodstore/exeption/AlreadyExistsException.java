package uz.example.foodstore.exeption;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String message) {
        super(message + " already exists");
    }
}
