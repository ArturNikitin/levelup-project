package database.DAO.impl;

import database.DAO.UserDAO;
import database.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOImplTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private UserDAO userDAO;


    @BeforeEach
    void setUp() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        userDAO = new UserDAOImpl(manager);
    }

    @AfterEach
    void tearDown() {
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

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
        String login = "user1";
        String email = "User@gmail.com";
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
        String login = "user3";
        String email = "Use3@gmail.com";
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
        int postcode = 123456;
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
        String login = "user4";
        String email = "User4@gmail.com";
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
        String login = "user4";
        String email = "User4@gmail.com";
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
        String login = "user6";
        String email = "User6@gmail.com";
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
        String login = "user7";
        String email = "User7@gmail.com";
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