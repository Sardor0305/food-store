package uz.example.foodstore.exeption;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String e) {
        super(e + " not found.");
    }
}
