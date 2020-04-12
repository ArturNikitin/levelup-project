package database.DAO.impl;

import database.DAO.ItemDAO;
import database.entities.Item;
import database.entities.Order;
import database.entities.User;
import database.utilities.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ItemDAOImplTest {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    ItemDAO itemDAO;

    @Test
    @Transactional
    void createItem() {
        String name = "item125";
        Price price = new Price(10.15);
        ClothingSize size = ClothingSize.S;
        ClothingType type = ClothingType.JACKET;

        String login = "us1er5221";
        String email = "Ur122425@gmail.com";
        String password = "1234";

        User user = new User(login, email, password);

        manager.persist(user);

        itemDAO.createItem(user, name, price, size, type);

        User foundUser = manager.find(User.class, user.getId());

        Item item = foundUser.getItems().get(0);

        assertNotNull(item);
        assertEquals(name, item.getName());
        assertEquals(price, item.getPrice());
        assertEquals(type, item.getType());
        assertEquals(ClothingStatus.AVAILABLE, item.getStatus());
        assertNull(item.getFile());
        assertNotEquals(0, item.getId());
    }

    @Test
    @Transactional
    void setType() {
        String name = "item1246";
        Price price = new Price(10.15);
        ClothingSize size = ClothingSize.S;
        ClothingType type = ClothingType.JACKET;

        String login = "user1264";
        String email = "Use3535r@gmail.com";
        String password = "1234";

        User user = new User(login, email, password);

        manager.persist(user);

        Item item = new Item(name, price, type, size, user);
        item.setStatus(ClothingStatus.AVAILABLE);

        manager.persist(item);

        itemDAO.setType(item, ClothingType.SHIRT);

        assertEquals(ClothingType.SHIRT, item.getType());
    }

    @Test
    @Transactional
    void connectToOrder() {
        Order order = new Order();

        String email = "email22@gmail.com";
        UserAddress address = new UserAddress("Country22", "City22", "Street22 15F124", "126298");
        order.setEmail(email);
        order.setAddress(address);
        order.setStatus(OrderStatus.PROCESSING);
        order.setDate(new Date());

        manager.persist(order);

        String name = "item1";
        Price price = new Price(10.15);
        ClothingSize size = ClothingSize.S;
        ClothingType type = ClothingType.JACKET;

        String login = "user176";
        String userEmail = "User472@gmail.com";
        String password = "1234";

        User user = new User(login, userEmail, password);

        manager.persist(user);

        Item item = new Item(name, price, type, size, user);
        item.setStatus(ClothingStatus.AVAILABLE);

        manager.persist(item);

        itemDAO.connectToOrder(item, order);

        assertEquals(item.getOrder(), order);

    }


    @Test
    @Transactional
    void setItemStatus() {
        String name = "item2";
        Price price = new Price(10.15);
        ClothingSize size = ClothingSize.S;
        ClothingType type = ClothingType.JACKET;

        String login = "user21";
        String email = "User@gmail.com";
        String password = "1234";

        User user = new User(login, email, password);

        manager.persist(user);

        Item item = new Item(name, price, type, size, user);
        item.setStatus(ClothingStatus.AVAILABLE);

        manager.persist(item);

        item.setStatus(ClothingStatus.SOLD_OUT);

        assertEquals(ClothingStatus.SOLD_OUT, item.getStatus());
    }

    @Test
    @Transactional
    void findItemByName() {
        String name = "item1232";
        Price price = new Price(10.15);
        ClothingSize size = ClothingSize.S;
        ClothingType type = ClothingType.JACKET;

        String login = "user217";
        String email = "User582@gmail.com";
        String password = "1234";

        User user = new User(login, email, password);

        manager.persist(user);

        Item item = new Item(name, price, type, size, user);
        item.setStatus(ClothingStatus.AVAILABLE);

        manager.persist(item);

        Item foundItem = itemDAO.findItemByName(name);
        assertNotNull(foundItem);
        assertEquals(foundItem, item);
    }

    @Test
    @Transactional
    void changeSize() {
        String name = "item12";
        Price price = new Price(103.15);
        ClothingSize size = ClothingSize.S;
        ClothingType type = ClothingType.JACKET;

        String login = "user221";
        String email = "Use2r@gmail.com";
        String password = "12334";

        User user = new User(login, email, password);

//        manager.getTransaction().begin();
        manager.persist(user);
//        manager.getTransaction().commit();

        Item item = new Item(name, price, type, size, user);
        item.setStatus(ClothingStatus.AVAILABLE);

//        manager.getTransaction().begin();
        manager.persist(item);
//        manager.getTransaction().commit();

        itemDAO.changeSize(item, ClothingSize.L);

        assertEquals(ClothingSize.L, item.getSize());
    }

    @Test
    @Transactional
    void addImage() {
        String name = "item23";
        Price price = new Price(104.15);
        ClothingSize size = ClothingSize.S;
        ClothingType type = ClothingType.JACKET;

        String login = "user212144";
        String email = "User352@gmail.com";
        String password = "1234";

        User user = new User(login, email, password);

        manager.persist(user);

        Item item = new Item(name, price, type, size, user);
        item.setStatus(ClothingStatus.AVAILABLE);

        manager.persist(item);

        File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg");

        itemDAO.addImage(item, file);

        assertEquals(file, item.getFile());

    }

    @Test
    @Transactional
    void addDescription() {
        String name = "item33";
        Price price = new Price(104.15);
        ClothingSize size = ClothingSize.S;
        ClothingType type = ClothingType.JACKET;

        String login = "user21490";
        String email = "User95@gmail.com";
        String password = "1234";

        User user = new User(login, email, password);

        manager.persist(user);

        Item item = new Item(name, price, type, size, user);
        item.setStatus(ClothingStatus.AVAILABLE);

        manager.persist(item);
        String description = "This is a beautiful skirt my friend";

        itemDAO.addDescription(item, description);

        assertEquals(description, item.getDescription());
    }
}