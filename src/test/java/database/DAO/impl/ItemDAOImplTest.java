package database.DAO.impl;

import database.DAO.ItemDAO;
import database.entities.Item;
import database.entities.Order;
import database.entities.User;
import database.utilities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.File;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ItemDAOImplTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    ItemDAO itemDAO;

    @BeforeEach
    void setUp() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        itemDAO = new ItemDAOImpl(manager);
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
    void createItem() {
        String name = "item1";
        Price price = new Price(10.15);
        ClothingSize size = ClothingSize.S;
        ClothingType type = ClothingType.JACKET;

        String login = "user1";
        String email = "User@gmail.com";
        String password = "1234";

        User user = new User(login, email, password);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        Item item = itemDAO.createItem(user, name, price, size, type);

        assertNotNull(item);
        assertEquals(name, item.getName());
        assertEquals(price, item.getPrice());
        assertEquals(type, item.getType());
        assertEquals(ClothingStatus.AVAILABLE, item.getStatus());
        assertNull(item.getFile());
        assertNotEquals(0, item.getId());
    }

    @Test
    void setType() {
        String name = "item1";
        Price price = new Price(10.15);
        ClothingSize size = ClothingSize.S;
        ClothingType type = ClothingType.JACKET;

        String login = "user1";
        String email = "User@gmail.com";
        String password = "1234";

        User user = new User(login, email, password);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        Item item = new Item(name, price, type, size, user);
        item.setStatus(ClothingStatus.AVAILABLE);

        manager.getTransaction().begin();
        manager.persist(item);
        manager.getTransaction().commit();

        itemDAO.setType(item, ClothingType.SHIRT);

        assertEquals(ClothingType.SHIRT, item.getType());
    }

    @Test
    void connectToOrder() {
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

        String name = "item1";
        Price price = new Price(10.15);
        ClothingSize size = ClothingSize.S;
        ClothingType type = ClothingType.JACKET;

        String login = "user1";
        String userEmail = "User@gmail.com";
        String password = "1234";

        User user = new User(login, userEmail, password);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        Item item = new Item(name, price, type, size, user);
        item.setStatus(ClothingStatus.AVAILABLE);

        manager.getTransaction().begin();
        manager.persist(item);
        manager.getTransaction().commit();

        itemDAO.connectToOrder(item, order);

        assertEquals(item.getOrder(), order);

    }


    @Test
    void setItemStatus() {
        String name = "item2";
        Price price = new Price(10.15);
        ClothingSize size = ClothingSize.S;
        ClothingType type = ClothingType.JACKET;

        String login = "user21";
        String email = "User@gmail.com";
        String password = "1234";

        User user = new User(login, email, password);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        Item item = new Item(name, price, type, size, user);
        item.setStatus(ClothingStatus.AVAILABLE);

        manager.getTransaction().begin();
        manager.persist(item);
        manager.getTransaction().commit();

        item.setStatus(ClothingStatus.SOLD_OUT);

        assertEquals(ClothingStatus.SOLD_OUT, item.getStatus());
    }

    @Test
    void findItemByName() {
        String name = "item12";
        Price price = new Price(10.15);
        ClothingSize size = ClothingSize.S;
        ClothingType type = ClothingType.JACKET;

        String login = "user21";
        String email = "User@gmail.com";
        String password = "1234";

        User user = new User(login, email, password);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        Item item = new Item(name, price, type, size, user);
        item.setStatus(ClothingStatus.AVAILABLE);

        manager.getTransaction().begin();
        manager.persist(item);
        manager.getTransaction().commit();

        Item foundItem = itemDAO.findItemByName(name);
        assertNotNull(foundItem);
        assertEquals(foundItem, item);
    }

    @Test
    void changeSize() {
        String name = "item12";
        Price price = new Price(103.15);
        ClothingSize size = ClothingSize.S;
        ClothingType type = ClothingType.JACKET;

        String login = "user221";
        String email = "Use2r@gmail.com";
        String password = "12334";

        User user = new User(login, email, password);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        Item item = new Item(name, price, type, size, user);
        item.setStatus(ClothingStatus.AVAILABLE);

        manager.getTransaction().begin();
        manager.persist(item);
        manager.getTransaction().commit();

        itemDAO.changeSize(item, ClothingSize.L);

        assertEquals(ClothingSize.L, item.getSize());
    }

    @Test
    void addImage() {
        String name = "item23";
        Price price = new Price(104.15);
        ClothingSize size = ClothingSize.S;
        ClothingType type = ClothingType.JACKET;

        String login = "user214";
        String email = "User@gmail.com";
        String password = "1234";

        User user = new User(login, email, password);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        Item item = new Item(name, price, type, size, user);
        item.setStatus(ClothingStatus.AVAILABLE);

        manager.getTransaction().begin();
        manager.persist(item);
        manager.getTransaction().commit();

        File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg");

        itemDAO.addImage(item, file);

        assertEquals(file, item.getFile());

    }

    @Test
    void addDescription() {
        String name = "item33";
        Price price = new Price(104.15);
        ClothingSize size = ClothingSize.S;
        ClothingType type = ClothingType.JACKET;

        String login = "user214";
        String email = "User@gmail.com";
        String password = "1234";

        User user = new User(login, email, password);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        Item item = new Item(name, price, type, size, user);
        item.setStatus(ClothingStatus.AVAILABLE);

        manager.getTransaction().begin();
        manager.persist(item);
        manager.getTransaction().commit();
        String description = "This is a beautiful skirt my friend";

        itemDAO.addDescription(item, description);

        assertEquals(description, item.getDescription());

    }
}