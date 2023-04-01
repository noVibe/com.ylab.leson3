package demonstrations;


import password_vallidator.PasswordValidator;

public class PasswordValidatorTest {
    public static void main(String[] args) {
        System.out.println(PasswordValidator.validate("SuperLogin_3000", "Password123", "Password123"));
        System.out.println(PasswordValidator.validate("SuperLogin_3000", "Password1231", "Password123"));
        System.out.println(PasswordValidator.validate("SuperLogin*3000", "Password1231", "Password123"));
        System.out.println(PasswordValidator.validate("SuperLogin_3000", "Pas$word1231", "Password123"));
        System.out.println(PasswordValidator.validate("SuperLogin_3000", "111111111111111111111111", "Password123"));
        System.out.println(PasswordValidator.validate("111111111111111111111", "Pas$word1231", "Password123"));
    }
}
