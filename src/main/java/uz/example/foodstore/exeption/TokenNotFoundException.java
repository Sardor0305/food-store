package uz.example.foodstore.exeption;

public class TokenNotFoundException extends RuntimeException{
    public TokenNotFoundException(String message) {
        super("token not found :" + message);
    }


}
