package database;

import database.DAO.ClientDAO;
import database.DAO.ItemDAO;
import database.DAO.impl.ClientDAOImpl;
import database.DAO.impl.ItemDAOImpl;
import database.entities.Client;
import database.entities.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PostgresPersistenceUnit");
        EntityManager manager = factory.createEntityManager();
        ClientDAO clientDAO = new ClientDAOImpl(manager);
        ItemDAO itemDAO = new ItemDAOImpl(manager);

        Client client = clientDAO.insertClient("login5", "login5@gmail.com", "12345");
        Item item = itemDAO.connectItemToClientById("Second Item", 4);

        manager.close();
        factory.close();
    }
}
