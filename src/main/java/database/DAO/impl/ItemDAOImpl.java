package database.DAO.impl;

import database.DAO.ItemDAO;
import database.entities.Client;
import database.entities.Item;

import javax.persistence.EntityManager;

public class ItemDAOImpl implements ItemDAO {
    private EntityManager entityManager;

    public ItemDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Item addItem(String name) {
        Item item = new Item();
        item.setName(name);

        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();

        return item;
    }

    @Override
    public Item connectItemToClientById(String name, int id) {
        Item item = new Item();
        item.setName(name);
        item.setClient(entityManager.find(Client.class, id));

        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();

        return item;
    }
}
