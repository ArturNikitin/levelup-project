package database.DAO.impl;

import database.DAO.OrderDAO;
import database.entities.Order;
import database.entities.User;
import database.utilities.OrderStatus;
import database.utilities.UserAddress;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Date;

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
    void createOrderWithUser() {
        String login = "user1";
        String email = "User@gmail.com";
        String password = "1234";

        User user = new User(login, email, password);
        UserAddress address = new UserAddress("Country3", "City3", "Street3 15F124", 156438);
        user.setAddress(address);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        Order order = orderDAO.createOrderWithUser(user);
        assertNotNull(order);
        assertEquals(email, order.getEmail());
        assertEquals(address, order.getAddress());
        assertEquals(user, order.getUser());
        assertNotNull(order.getDate());
        assertNotEquals(0, order.getId());
        assertEquals(OrderStatus.PROCESSING, order.getStatus());
    }

    @Test
    void createOrderWithUserWithoutAddress() {
        String login = "user1";
        String email = "User@gmail.com";
        String password = "1234";

        User user = new User(login, email, password);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        try {
            Order order = orderDAO.createOrderWithUser(user);
            fail("createOrderWithUser should fail for null user's address");
        } catch (Exception exp){

        }

    }


    @Test
    void CreateOrder() {
        String email = "email@gmail.com";
        UserAddress address = new UserAddress("Country2", "City2", "Street2 15F124", 156298);

        Order order = orderDAO.createOrder(email, address);
        assertNotNull(order);
        assertNotEquals(0, order.getId());
        assertEquals(email, order.getEmail());
        assertEquals(address, order.getAddress());
        assertNotNull(order.getDate());
    }

    @Test
    void cancelOrder() {
        Order order = new Order();

        String email = "email22@gmail.com";
        UserAddress address = new UserAddress("Country22", "City22", "Street22 15F124", 126298);
        order.setEmail(email);
        order.setAddress(address);
        order.setStatus(OrderStatus.PROCESSING);
        order.setDate(new Date());

        manager.getTransaction().begin();
        manager.persist(order);
        manager.getTransaction().commit();

        Order updatedOrder = orderDAO.cancelOrder(order.getId());

        assertNotNull(updatedOrder);
        assertEquals(OrderStatus.CANCELLED, updatedOrder.getStatus());
    }

    @Test
    void updateStatusToShipped() {
        Order order = new Order();

        String email = "email12@gmail.com";
        UserAddress address = new UserAddress("Country221", "City212", "Street212 15F124", 121298);
        order.setEmail(email);
        order.setAddress(address);
        order.setStatus(OrderStatus.PROCESSING);
        order.setDate(new Date());

        manager.getTransaction().begin();
        manager.persist(order);
        manager.getTransaction().commit();

        Order updatedOrder = orderDAO.updateStatusToShipped(order.getId());

        assertNotNull(updatedOrder);
        assertEquals(OrderStatus.SHIPPED, updatedOrder.getStatus());
    }

    @Test
    void findOrderById() {
        Order order = new Order();

        String email = "email132@gmail.com";
        UserAddress address = new UserAddress("Country3221", "City2123", "Street2132 15F124", 121398);
        order.setEmail(email);
        order.setAddress(address);
        order.setStatus(OrderStatus.PROCESSING);
        order.setDate(new Date());

        manager.getTransaction().begin();
        manager.persist(order);
        manager.getTransaction().commit();

        Order foundOrder = orderDAO.findOrderById(order.getId());
        assertNotNull(foundOrder);
        assertEquals(email, foundOrder.getEmail());
        assertEquals(address, order.getAddress());
        assertEquals(order.getStatus(), foundOrder.getStatus());
        assertEquals(order.getId(), foundOrder.getId());
    }

    @Test
    void findOrderByIdWrongId(){
        Order order = new Order();

        String email = "emai2@gmail.com";
        UserAddress address = new UserAddress("Country31", "City23", "Street22 15F24", 121368);
        order.setEmail(email);
        order.setAddress(address);
        order.setStatus(OrderStatus.PROCESSING);
        order.setDate(new Date());

        manager.getTransaction().begin();
        manager.persist(order);
        manager.getTransaction().commit();

        Order foundOrder = orderDAO.findOrderById(order.getId());
        assertNotNull(foundOrder);
        assertEquals(email, foundOrder.getEmail());
        assertEquals(address, order.getAddress());
        assertEquals(order.getStatus(), foundOrder.getStatus());
        assertNotEquals(order.getId()+1, foundOrder.getId());
    }
}