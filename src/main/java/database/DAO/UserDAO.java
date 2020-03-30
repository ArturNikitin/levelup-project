package database.DAO;

import database.entities.User;


public interface UserDAO {
    User insertUser(String login, String email, String password);
    User findUserByLogin(String login);
    User addAddress(User user, String country, String city, String street, String postcode);
    User updateUserPassword(User user, String password, String newPassword);
    boolean validatePassword(User user, String password);
    void removeUser(User user, String password);
}
