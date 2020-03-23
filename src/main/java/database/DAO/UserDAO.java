package database.DAO;

import database.entities.Item;
import database.entities.Order;
import database.entities.User;
import database.utilities.UserAddress;

import java.util.List;


public interface UserDAO {
    User insertUser(String login, String email, String password);
    User findUserByLogin(String login);
    User addAddress(User user, String country, String city, String street, int postcode);
    User updateUserPassword(User user, String password, String newPassword);
    boolean validatePassword(User user, String password);
    void removeUser(User user, String password);
}
