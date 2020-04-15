package webjava;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {
    @Size(min = 4, max = 50, message = "Login cannot be shorter than 4 and longer than 50")
    @Pattern(regexp = "[\\w_.-0-9]*", message = "Only letters, digits and '_' '-' '.' are allowed ")
    private String login;

    @Email(message = "Not valid email")
    private String email;

    @Size(min = 6, max = 50, message = "Password cannot be shorter than 4 and longer than 50")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]*", message = "Password must contain at least one small character, one big character and a number")
    private String passwrod;
}
