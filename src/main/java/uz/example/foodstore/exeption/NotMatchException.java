package uz.example.foodstore.exeption;

public class NotMatchException extends RuntimeException{
    public NotMatchException(String e) {
        super(e + " not match.");
    }
}
