package database.DAO.impl;

import database.DAO.OrderDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOImplTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private OrderDAO orderDAO;

    @BeforeEach
    void setUp() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        orderDAO = new OrderDAOImpl(manager);
    }

    @AfterEach
    void tearDown() {
        if(manager != null){
            manager.close();
        }
        if (factory != null){
            factory.close();
        }
    }

    @Test
    void createOrder() {
    }

    @Test
    void testCreateOrder() {
    }

    @Test
    void cancelOrder() {
    }

    @Test
    void updateStatusToShipped() {
    }

    @Test
    void findOrderById() {
    }
}