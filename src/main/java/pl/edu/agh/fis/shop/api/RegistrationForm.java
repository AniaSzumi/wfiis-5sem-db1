package pl.edu.agh.fis.shop.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationForm {
    private String name;
    private String surname;
    private String email;
    private String password;
    private int managerId;
    private int shopId;
}
