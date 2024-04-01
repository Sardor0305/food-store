package uz.example.foodstore.exeption;


public class TokenIsExpiredException extends RuntimeException{

    public TokenIsExpiredException() {
        super("Token is expired : ");
    }

}
