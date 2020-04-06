package database.DAO.impl;

import database.DAO.OrderDAO;
import database.entities.Order;
import database.entities.User;
import database.utilities.OrderStatus;
import database.utilities.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.Objects;

@Repository
public class OrderDAOImpl implements OrderDAO {
    @PersistenceContext
    private EntityManager manager;


    @Override
    @Transactional
    public Order createOrderWithUser(User user) {
        Objects.requireNonNull(user.getAddress());
        Order order = new Order();
        order.setStatus(OrderStatus.PROCESSING);
        order.setUser(user);
        order.setEmail(user.getEmail());
        order.setAddress(user.getAddress());

        manager.persist(order);
        return order;
    }

    @Override
    @Transactional
    public Order createOrder(String email, UserAddress address) {
        Order order = new Order();
        order.setStatus(OrderStatus.PROCESSING);
        order.setEmail(email);
        order.setAddress(address);
        order.setDate(new Date());

        manager.persist(order);
        return order;
    }


    @Override
    @Transactional
    public Order cancelOrder(int id) {
        Order order;

        order = manager.find(Order.class, id);
        order.setStatus(OrderStatus.CANCELLED);

        return order;
    }


    @Override
    @Transactional
    public Order updateStatusToShipped(int id) {
        Order order;

        order = manager.find(Order.class, id);
        order.setStatus(OrderStatus.SHIPPED);

        return order;
    }

    @Override
    @Transactional
    public Order findOrderById(int id) {
        return manager.find(Order.class, id);
    }
}
