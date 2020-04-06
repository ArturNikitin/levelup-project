package database.DAO.impl;

import database.DAO.ItemDAO;
import database.entities.Item;
import database.entities.Order;
import database.entities.User;
import database.utilities.ClothingSize;
import database.utilities.ClothingStatus;
import database.utilities.ClothingType;
import database.utilities.Price;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

@Repository
public class ItemDAOImpl implements ItemDAO {
    /*private EntityManager manager;

    public ItemDAOImpl(@Autowired EntityManager manager) {
        this.manager = manager;
    }*/
    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public Item createItem(User user, String name, Price price, ClothingSize size, ClothingType type) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setUser(user);
        item.setStatus(ClothingStatus.AVAILABLE);
        item.setSize(size);
        item.setType(type);

        manager.persist(item);
        if (user.getItems() == null) {
            ArrayList<Item> items = new ArrayList<>();
            items.add(item);
            user.setItems(items);
        } else {
            user.getItems().add(item);
        }

        return item;
    }

    @Override
    @Transactional
    public Item setType(Item item, ClothingType type) {
        Objects.requireNonNull(type, "address can't be null");
        item.setType(type);
        return item;
    }

    @Override
    @Transactional
    public Item connectToOrder(Item item, Order order) {
        Objects.requireNonNull(order, "address can't be null");
        item.setOrder(order);
        return item;
    }

    @Override
    @Transactional
    public Item setItemStatus(Item item, ClothingStatus status) {
        item.setStatus(status);
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
    @Transactional
    public Item changeSize(Item item, ClothingSize size) {
        item.setSize(size);
        return item;
    }

    @Override
    @Transactional
    public Item addImage(Item item, File file) {
        item.setFile(file);
        return item;
    }

    @Override
    @Transactional
    public Item addDescription(Item item, String description) {
        item.setDescription(description);
        return item;
    }
}
