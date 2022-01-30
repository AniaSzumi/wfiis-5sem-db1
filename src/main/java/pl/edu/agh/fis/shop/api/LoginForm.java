package pl.edu.agh.fis.shop.api;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Klasa przekazująca dane z logowania
 */
@Data
@AllArgsConstructor
public class LoginForm {
    private String email;
    private String password;
}
