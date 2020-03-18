package database.DAO.impl;

import database.entities.Client;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class ClientDAOImplTest {
    static EntityManager manager;
    static EntityManagerFactory factory;
    static ClientDAOImpl clientDAO;

    @BeforeAll
    static void setUp() {
        factory = Persistence.createEntityManagerFactory("PostgresPersistenceUnitTest");
        manager = factory.createEntityManager();
        clientDAO = new ClientDAOImpl(manager);
    }

    @AfterAll
    static void tearDown() {
        if(manager != null){
            manager.close();
        }
        if (factory != null){
            factory.close();
        }
    }

    @Test
    void insertClient_ValidParameters() {
        String login = "name";
        String password = "1234";
        String email = "name@gmail.com";

        Client client = clientDAO.insertClient(login, email, password);
        assertEquals(login, client.getLogin());
        assertEquals(password, client.getPassword());
        assertEquals(email, client.getEmail());
    }

    @Test
    void removeClientById() {
    }
}