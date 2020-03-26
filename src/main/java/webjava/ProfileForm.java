package webjava;

import database.utilities.UserAddress;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileForm {
    private UserAddress address;
}
