package database.DAO.impl;

import database.DAO.ItemDAO;
import database.entities.Item;
import database.entities.Order;
import database.entities.User;
import database.utilities.ClothingSize;
import database.utilities.ClothingStatus;
import database.utilities.ClothingType;
import database.utilities.Price;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Objects;

public class ItemDAOImpl implements ItemDAO {
    private EntityManager manager;

    public ItemDAOImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Item createItem(User user, String name, Price price, ClothingSize size, ClothingType type) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setUser(user);
        item.setStatus(ClothingStatus.AVAILABLE);
        item.setSize(size);
        item.setType(type);

        manager.getTransaction().begin();
        try {
            manager.persist(item);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();


        return item;
    }

    @Override
    public Item setType(Item item, ClothingType type) {
        Objects.requireNonNull(type, "address can't be null");

        item.setType(type);

        manager.getTransaction().begin();
        try {
            manager.merge(item);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return item;
    }

    @Override
    public Item connectToOrder(Item item, Order order) {
        Objects.requireNonNull(order, "address can't be null");

        item.setOrder(order);

        manager.getTransaction().begin();
        try {
            manager.merge(item);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return item;
    }

    @Override
    public Item setItemSold(Item item) {
        item.setStatus(ClothingStatus.SOLD_OUT);

        manager.getTransaction().begin();
        try {
            manager.merge(item);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return item;
    }

    @Override
    public Item setItemAvailable(Item item) {
        item.setStatus(ClothingStatus.AVAILABLE);

        manager.getTransaction().begin();
        try {
            manager.merge(item);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return item;
    }

    @Override
    public Item setItemOrdered(Item item) {
        item.setStatus(ClothingStatus.ORDERED);

        manager.getTransaction().begin();
        try {
            manager.merge(item);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return item;
    }

    @Override
    public Item findItemById(int id) {
        return manager.find(Item.class, id);
    }

    @Override
    public Item findItemByName(String name) {
        try {
            return manager.createQuery("from Item i where i.name = :searchName", Item.class)
                    .setParameter("searchName", name)
                    .getSingleResult();
        } catch (NoResultException cause){
            return null;
        }
    }

    @Override
    public Item approveItem(Item item) {
        item.setStatus(ClothingStatus.AVAILABLE_AND_APPROVED);

        manager.getTransaction().begin();
        try {
            manager.merge(item);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return item;
    }
}
