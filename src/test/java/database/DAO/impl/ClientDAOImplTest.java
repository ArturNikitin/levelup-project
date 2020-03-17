package database.DAO.impl;

import database.entities.Client;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class ClientDAOImplTest {
    private EntityManagerFactory factory;
    private EntityManager manager;


    @BeforeEach
    void setUp() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
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
    public void insertClient() {
        String name = "name";
        String password = "pass";
        String email = "email";

        Client client = new Client();
        client.setLogin(name);
        client.setEmail(email);
        client.setPassword(password);

        manager.getTransaction().begin();
        manager.persist(client);
        manager.getTransaction().commit();
    }

    @Test
    void removeClientById() {
    }
}