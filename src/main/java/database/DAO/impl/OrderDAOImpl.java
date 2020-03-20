package database.DAO.impl;

import database.DAO.OrderDAO;
import database.entities.Item;
import database.entities.Order;
import database.entities.User;
import database.utilities.OrderStatus;
import database.utilities.UserAddress;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private EntityManager manager;

    public OrderDAOImpl(EntityManager manager) {
        this.manager = manager;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public Order createOrder(User user) {
        Order order = new Order();
        order.setStatus(OrderStatus.PROCESSING);
        order.setUser(user);
        order.setEmail(user.getEmail());
        order.setAddress(user.getAddress());
        order.setDate(new Date());

        manager.getTransaction().begin();
        try {
            manager.persist(order);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return order;
    }

    @Override
    public Order createOrder(String email, UserAddress address) {
        Order order = new Order();
        order.setStatus(OrderStatus.PROCESSING);
        order.setEmail(email);
        order.setAddress(address);
        order.setDate(new Date());

        manager.getTransaction().begin();
        try {
            manager.persist(order);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return order;
    }


    @Override
    public Order cancelOrder(int id) {
        Order order = manager.find(Order.class, id);
        order.setStatus(OrderStatus.CANCELLED);
        manager.getTransaction().begin();
        try {
            manager.merge(order);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return order;
    }


    @Override
    public Order updateStatusToShipped(int id) {
        Order order = manager.find(Order.class, id);
        order.setStatus(OrderStatus.SHIPPED);
        manager.getTransaction().begin();
        try {
            manager.merge(order);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return order;
    }

    @Override
    public Order findOrderById(int id) {
        return manager.find(Order.class, id);
    }
}
