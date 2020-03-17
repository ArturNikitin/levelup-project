package database;

import database.DAO.ClientDAO;
import database.DAO.impl.ClientDAOImpl;
import database.entities.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PostgresPersistenceUnit");
        EntityManager manager = factory.createEntityManager();
        ClientDAO clientDAO = new ClientDAOImpl(manager);

        manager.close();
        factory.close();
    }
}
