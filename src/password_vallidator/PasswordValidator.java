package password_vallidator;

import password_vallidator.exceptions.EmptyFieldException;
import password_vallidator.exceptions.WrongLoginException;
import password_vallidator.exceptions.WrongPasswordException;

public class PasswordValidator {

    public static boolean validate(String login, String password, String confirmPassword) {
        try {
            if (login.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                throw new EmptyFieldException("Fields can't be empty");
            }
            if (!login.matches("\\w*")) {
                throw new WrongLoginException("Login contains invalid symbols");
            }
            if (login.length() >= 20) {
                throw new WrongLoginException("Login is too long");
            }
            if (!password.matches("\\w*")) {
                throw new WrongPasswordException("Password contains invalid symbols");
            }
            if (password.length() >= 20) {
                throw new WrongPasswordException("Password is too long");
            }
            if (!password.equals(confirmPassword)) {
                throw new WrongPasswordException("Confirmation and password mismatch");
            }

        } catch (WrongLoginException | WrongPasswordException | EmptyFieldException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
