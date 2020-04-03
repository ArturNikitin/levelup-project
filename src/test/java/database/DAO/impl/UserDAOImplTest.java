package database.DAO.impl;

import database.DAO.UserDAO;
import database.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserDAOImplTest {
    //    private EntityManagerFactory factory;
    @Autowired
    private EntityManager manager;

    @Autowired
    private UserDAO userDAO;

    @Test
    void findUserByLogin() {
        String login = "user2";
        String email = "User2@gmail.com";
        String password = "12345";
        User user = new User(login, email, password);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        User found = userDAO.findUserByLogin(login);
        assertNotNull(found);
        assertEquals(login, found.getLogin());
        assertEquals(email, found.getEmail());
        assertEquals(password, found.getPassword());
    }

    @Test
    void insertUser() {
        String login = "user194";
        String email = "User48@gmail.com";
        String password = "1234";

        User user = userDAO.insertUser(login, email, password);
        assertNotNull(user);

        assertNotEquals(0, user.getId());
        assertEquals(login, user.getLogin());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    @Test
    void insertUserDuplicate() {
        String login = "user33";
        String email = "Us456e3@gmail.com";
        String password = "1234";
        User user = userDAO.insertUser(login, email, password);

        try {
            User duplicate = userDAO.insertUser(login, "random@gmail.com", password);
            fail("insertUser should fail for same login");
        } catch (PersistenceException exp) {
        }

        try {
            User duplicate = userDAO.insertUser("randomName", email, password);
            fail("insertUser should fail for same email");
        } catch (PersistenceException exp) {
        }

    }

    @Test
    void updateAddress() {
        String country = "Country";
        String city = "city";
        String street = "street";
        String postcode = "123456";
        User user = new User("login", "email", "password");

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        userDAO.addAddress(user, country, city, street, postcode);

        User found = manager.find(User.class, user.getId());

        assertNotNull(found.getAddress());
        assertEquals(country, found.getAddress().getCountry());
        assertEquals(city, found.getAddress().getCity());
        assertEquals(street, found.getAddress().getStreet());
        assertEquals(postcode, found.getAddress().getPostCode());
    }

    @Test
    void updateUserPassword() {
        String login = "user44763";
        String email = "User524@gmail.com";
        String password = "12345";
        User user = new User(login, email, password);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        String newPassword = "12345678";
        User updated = userDAO.updateUserPassword(user, password, newPassword);

        assertNotNull(updated.getPassword());
        assertEquals(newPassword, updated.getPassword());
    }

    @Test
    void updateUserPasswordWrongPassword() {
        String login = "user46374";
        String email = "User464@gmail.com";
        String password = "12345";
        User user = new User(login, email, password);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        String newPassword = "12345678";
        try {
            User updated = userDAO.updateUserPassword(user, "11", newPassword);
            fail("updateUserPassword should fail for wrong password");
        } catch (Exception exp) {

        }
    }

    @Test
    void removeUser() {
        String login = "user5";
        String email = "User5@gmail.com";
        String password = "12345";
        User user = new User(login, email, password);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        int id = user.getId();

        userDAO.removeUser(user, password);


        User user1 = manager.find(User.class, id);
        assertNull(user1);
    }

    @Test
    void removeUserIncorrectPassword() {
        String login = "user5";
        String email = "User5@gmail.com";
        String password = "12345";
        User user = new User(login, email, password);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        int id = user.getId();

        try {
            userDAO.removeUser(user, "password");
            fail("removeUser should fail for wrong password");
        } catch (IllegalArgumentException exp) {

        }

    }


    @Test
    void validatePassword() {
        String login = "user3527";
        String email = "User73@gmail.com";
        String password = "12345";
        User user = new User(login, email, password);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        assertTrue(userDAO.validatePassword(user, password));
    }

    @Test
    void validatePasswordWrongPassword() {
        String login = "user7";
        String email = "User7@gmail.com";
        String password = "12345";
        User user = new User(login, email, password);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        assertFalse(userDAO.validatePassword(user, "password"));
    }
}