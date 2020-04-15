package database.entities;

import database.utilities.UserAddress;
import database.utilities.UserAddressConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    @Size(min = 4, max = 50, message = "Login cannot be shorter than 4 and longer than 50")
    @Pattern(regexp = "[\\w_.-0-9]*", message = "Only letters, digits and '_' '-' '.' are allowed ")
    private String login;

    @Column(nullable = false)
    @Size(min = 6, max = 50, message = "Password cannot be shorter than 4 and longer than 50")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]*", message = "Password must contain at least one small character, one bi character and a number")
    private String password;

    @Column(nullable = false, unique = true)
    @Email(message = "Not valid email")
    private String email;

    @Column
    @Convert(converter = UserAddressConverter.class)
    private UserAddress address;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Order> orders;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Item> items;

    public User(String login, String email, String password){
        this.login = login;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, address, orders, items);
    }
}
