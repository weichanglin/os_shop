package club.osnote.os_shop.jwt;

public class LoginException extends RuntimeException{
    public LoginException(String message) {
        super(message);
    }
}
