package database.DAO.impl;

import database.DAO.ItemDAO;
import database.entities.Item;
import database.entities.Order;
import database.entities.User;
import database.utilities.ClothingSize;
import database.utilities.ClothingStatus;
import database.utilities.ClothingType;
import database.utilities.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.File;
import java.util.Objects;

@Repository
public class ItemDAOImpl implements ItemDAO {
    private EntityManager manager;

    public ItemDAOImpl(@Autowired EntityManager manager) {
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



        manager.getTransaction().begin();
        try {
            item.setType(type);
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



        manager.getTransaction().begin();
        try {
            item.setOrder(order);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return item;
    }

    @Override
    public Item setItemStatus(Item item, ClothingStatus status) {


        manager.getTransaction().begin();
        try {
            item.setStatus(status);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return item;
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
    public Item changeSize(Item item, ClothingSize size) {


        manager.getTransaction().begin();
        try {
            item.setSize(size);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return item;
    }

    @Override
    public Item addImage(Item item, File file) {
        manager.getTransaction().begin();
        try {
            item.setFile(file);
        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return item;
    }

    @Override
    public Item addDescription(Item item, String description) {
        manager.getTransaction().begin();
        try {
            item.setDescription(description);

        } catch (Throwable cause){
            manager.getTransaction().rollback();
            throw cause;
        }
        manager.getTransaction().commit();

        return item;
    }
}
