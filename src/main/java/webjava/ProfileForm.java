package webjava;

import database.entities.Item;
import database.utilities.UserAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileForm {
    private UserAddress address;
    private List<Item> items;

    public ProfileForm(UserAddress address){
        this.address = address;
    }
}
