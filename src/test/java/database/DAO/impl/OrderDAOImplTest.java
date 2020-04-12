package database.DAO.impl;

import database.DAO.OrderDAO;
import database.entities.Order;
import database.entities.User;
import database.utilities.OrderStatus;
import database.utilities.UserAddress;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderDAOImplTest {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private OrderDAO orderDAO;

    @Test
    @Transactional
    void createOrderWithUser() {
        String login = "use6434r1";
        String email = "Use456r@gmail.com";
        String password = "1234";

        User user = new User(login, email, password);
        UserAddress address = new UserAddress("Country3", "City3", "Street3 15F124", "156438");
        user.setAddress(address);

        manager.persist(user);

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
    @Transactional
    void createOrderWithUserWithoutAddress() {
        String login = "user1324";
        String email = "Use4325r@gmail.com";
        String password = "1234";

        User user = new User(login, email, password);

        manager.persist(user);

        try {
            Order order = orderDAO.createOrderWithUser(user);
            fail("createOrderWithUser should fail for null user's address");
        } catch (Exception exp) {

        }

    }


    @Test
    @Transactional
    void CreateOrder() {
        String email = "email@gmail.com";
        UserAddress address = new UserAddress("Country2", "City2", "Street2 15F124", "156298");

        Order order = orderDAO.createOrder(email, address);
        assertNotNull(order);
        assertNotEquals(0, order.getId());
        assertEquals(email, order.getEmail());
        assertEquals(address, order.getAddress());
        assertNotNull(order.getDate());
    }

    @Test
    @Transactional
    void cancelOrder() {
        Order order = new Order();

        String email = "email22@gmail.com";
        UserAddress address = new UserAddress("Country22", "City22", "Street22 15F124", "126298");
        order.setEmail(email);
        order.setAddress(address);
        order.setStatus(OrderStatus.PROCESSING);
        order.setDate(new Date());

        manager.persist(order);

        Order updatedOrder = orderDAO.cancelOrder(order.getId());

        assertNotNull(updatedOrder);
        assertEquals(OrderStatus.CANCELLED, updatedOrder.getStatus());
    }

    @Test
    @Transactional
    void updateStatusToShipped() {
        Order order = new Order();

        String email = "email12@gmail.com";
        UserAddress address = new UserAddress("Country221", "City212", "Street212 15F124", "121298");
        order.setEmail(email);
        order.setAddress(address);
        order.setStatus(OrderStatus.PROCESSING);
        order.setDate(new Date());

        manager.persist(order);

        Order updatedOrder = orderDAO.updateStatusToShipped(order.getId());

        assertNotNull(updatedOrder);
        assertEquals(OrderStatus.SHIPPED, updatedOrder.getStatus());
    }

    @Test
    @Transactional
    void findOrderById() {
        Order order = new Order();

        String email = "email132@gmail.com";
        UserAddress address = new UserAddress("Country3221", "City2123", "Street2132 15F124", "121398");
        order.setEmail(email);
        order.setAddress(address);
        order.setStatus(OrderStatus.PROCESSING);
        order.setDate(new Date());

        manager.persist(order);

        Order foundOrder = orderDAO.findOrderById(order.getId());
        assertNotNull(foundOrder);
        assertEquals(email, foundOrder.getEmail());
        assertEquals(address, order.getAddress());
        assertEquals(order.getStatus(), foundOrder.getStatus());
        assertEquals(order.getId(), foundOrder.getId());
    }

    @Test
    @Transactional
    void findOrderByIdWrongId() {
        Order order = new Order();

        String email = "emai2@gmail.com";
        UserAddress address = new UserAddress("Country31", "City23", "Street22 15F24", "121368");
        order.setEmail(email);
        order.setAddress(address);
        order.setStatus(OrderStatus.PROCESSING);
        order.setDate(new Date());

        manager.persist(order);

        Order foundOrder = orderDAO.findOrderById(order.getId());
        assertNotNull(foundOrder);
        assertEquals(email, foundOrder.getEmail());
        assertEquals(address, order.getAddress());
        assertEquals(order.getStatus(), foundOrder.getStatus());
        assertNotEquals(order.getId() + 1, foundOrder.getId());
    }
}