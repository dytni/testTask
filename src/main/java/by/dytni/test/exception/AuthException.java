package by.dytni.test.exception;

/**
 * Исключение используется для ошибок аутентификации и авторизации.
 *
 * @author upagge 13.10.2024
 */

public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }

}
