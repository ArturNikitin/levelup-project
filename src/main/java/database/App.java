package database;



import database.DAO.ItemDAO;
import database.DAO.OrderDAO;
import database.DAO.UserDAO;
import database.DAO.impl.ItemDAOImpl;
import database.DAO.impl.OrderDAOImpl;
import database.DAO.impl.UserDAOImpl;
import database.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        /*EntityManagerFactory factory = Persistence.createEntityManagerFactory("PostgresPersistenceUnit");
        EntityManager manager = factory.createEntityManager();
        UserDAO userDAO = new UserDAOImpl(manager);
        OrderDAO orderDAO = new OrderDAOImpl(manager);
        ItemDAO itemDAO = new ItemDAOImpl(manager);*/

//        User user = userDAO.insertUser("testr", "testuser@gmail.com", "123");
//        userDAO.updateAddress(userDAO.findUserByLogin("Artur"), new UserAddress("Russia", "SPb", "9 sovetskaya dom 10 kv 15", 194785));
//        userDAO.updateUserPassword(userDAO.findUserByLogin("Artur"), "12345678", "123456789");
//        System.out.println(userDAO.validatePassword(userDAO.findUserByLogin("Artur"), "123456789"));
//        userDAO.removeUser(userDAO.findUserByLogin("Artur22"), "1234");

//        orderDAO.createOrderWithUser(userDAO.findUserByLogin("Artur"));
//        orderDAO.createOrder("random15@gmail.com", new UserAddress("Serbi", "Zagreb", "Vrna", 123487));

//        itemDAO.createItem(userDAO.findUserByLogin("Artur"), "Skirt", new Price(20.1564), ClothingSize.S, ClothingType.HOODIES);

//       List<Item> items = userDAO.findUserByLogin("Artur").getItems();
//       items.forEach(ex -> System.out.println(ex.getName()));


        /*manager.close();
        factory.close();*/
    }
}
