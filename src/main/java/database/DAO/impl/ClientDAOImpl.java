package database.DAO.impl;

import database.DAO.ClientDAO;
import database.entities.Client;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private EntityManager entityManager;

    public ClientDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void insertClient(String login, String email, String password) {
        Client client = new Client();
        client.setLogin(login);
        client.setEmail(email);
        client.setPassword(password);
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
    }
    @Override
    public void removeClientById(int id){
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Client.class, id));
        entityManager.getTransaction().commit();
    }

}
